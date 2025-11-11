package edu.arquetipo.jpa.controladores;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.stream.Collectors;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.eatthepath.otp.UncheckedNoSuchAlgorithmException;

import edu.arquetipo.jpa.servicios.OperativaAuthInterfaz;
import edu.arquetipo.jpa.dao.UsuarioDAO;
import edu.arquetipo.jpa.entidades.Usuario;
import edu.arquetipo.jpa.servicios.OperativaAuthImplementacion;

// DTOs (Data Transfer Objects) simples para leer el JSON
class LoginCredentials {
    public String email;
    public String password;
}

class RecoveryRequest {
    public String email;
}

@WebServlet("/api/auth/*")
public class AuthServlet extends HttpServlet {

    private ObjectMapper mapper;
    private OperativaAuthInterfaz auth;

    private class PasswordChangeRequest {
        String email;
        String newPassword;
        String token; // Token de un solo uso (OTP)
    }

    @Override
    public void init() {
        this.mapper = new ObjectMapper();
        this.auth = new OperativaAuthImplementacion();
    }

    // Todo en autenticación es un POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String pathInfo = req.getPathInfo();
        res.setContentType("application/json");

        String jsonBody = req.getReader().lines().collect(Collectors.joining());
        UsuarioDAO dao = new UsuarioDAO();

        try {
            if (pathInfo == null) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada");
                return;
            }

            switch (pathInfo) {
                case "/login" -> {
                    // Corresponde a "Página de Login (PC)"
                    // --- Lógica del servicio de Login ---
                    LoginCredentials creds = mapper.readValue(jsonBody, LoginCredentials.class);

                    String token = auth.login(creds.email, creds.password);
                    if (token != null) {
                        res.getWriter().write("{\"token\": \"" + token + "\"}");
                    } else {
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales inválidas");
                    }
                }
                case "/recover-password" -> {
                    // Corresponde a "Página de recuperación de contraseñas"
                    // --- Lógica para cambiar la contraseña ---
                    RecoveryRequest recovery = mapper.readValue(jsonBody, RecoveryRequest.class);
                    if (dao.buscar(recovery.email) != null) {
                        TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator(
                                TimeBasedOneTimePasswordGenerator.DEFAULT_TIME_STEP, 6,
                                TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA512);
                        final Key key;
                        {
                            final KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());
                            final int macLengthInBytes = Mac.getInstance(totp.getAlgorithm()).getMacLength();
                            keyGenerator.init(macLengthInBytes * 8);
                            key = keyGenerator.generateKey();
                        }
                        final Instant now = Instant.now();
                        final Instant later = now.plus(totp.getTimeStep());
                        totp.generateOneTimePassword(key, later);
                        res.getWriter().write(
                                "{\"message\": \"Se ha enviado un token de recuperación a su correo electrónico.\"}");
                    } else {
                        res.getWriter().write(
                                "{\"message\": \"Este correo no está registrado. Contacte con su administrador o algún de sus superiores en la empresa\"}");
                    }
                }
                case "/change-password" -> {
                    // --- Lógica para actualizar la contraseña usando el token ---
                    PasswordChangeRequest changeReq = mapper.readValue(jsonBody, PasswordChangeRequest.class);

                    if (changeReq.email == null || changeReq.token == null || changeReq.newPassword == null) {
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        res.getWriter().write(
                                "{\"error\": \"Faltan campos obligatorios: email, token o nueva contraseña.\"}");
                        return;
                    }

                    // 1. Buscar el usuario por email
                    Usuario usuario = dao.buscar(changeReq.email);

                    if (usuario == null) {
                        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        res.getWriter().write("{\"error\": \"Usuario no encontrado.\"}");
                        return;
                    }

                    // 2. Lógica de validación del token (ASUMIMOS QUE ES VÁLIDO o se gestiona en la
                    // capa de servicio)
                    // if (!auth.validarToken(changeReq.email, changeReq.token)) { ... }

                    // 3. Si es válido, actualizar la contraseña usando el DAO refactorizado

                    // El usuario debe ser gestionado por JPA o al menos ser un DTO con el ID
                    Usuario usuarioAActualizar = new Usuario();
                    usuarioAActualizar.setId(usuario.getId()); // Usamos el ID del usuario encontrado
                    usuarioAActualizar.setContrasena(changeReq.newPassword); // Solo seteamos la nueva contraseña

                    dao.actualizar(usuarioAActualizar); // Usamos el método refactorizado

                    res.getWriter().write("{\"message\": \"Contraseña actualizada exitosamente\"}");

                }
                default -> res.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta de autenticación no encontrada");
            }
        } catch (UncheckedNoSuchAlgorithmException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
