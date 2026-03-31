package cjfw.auth.ext;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cjfw.core.utility.EncryptionUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalAuthFilter extends OncePerRequestFilter {

	private final WhiteListProperties whiteListProps;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    	EncryptionUtils crypto = new EncryptionUtils();
//        String authKey = request.getHeader("Interface_auth_key");
        String authKey = "jooKuTMkQwKbsjaWlthdYA=="; // Nwill
        String clientIp = getClientIp(request);
        String company = crypto.decrypt(authKey);
        
        // 회사명 체크
        WhiteListProperties.Company found =
                whiteListProps.getCompanies().stream()
                        .filter(c -> c.getName().equals(company))
                        .findFirst()
                        .orElse(null);

        if (found == null) {
            response.sendError(403, "Invalid Company");
            return;
        }

        // IP 체크
        if (!found.getIps().contains(clientIp)) {
            response.sendError(403, "Invalid IP for this Company");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        return (ip != null) ? ip.split(",")[0].trim() : request.getRemoteAddr();
    }


}