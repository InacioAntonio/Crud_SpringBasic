package com.example.demo.Visao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/lista/*", "/pessoa/*", "/excluir/*" , "/excluir/*"})
public class FiltroAutenticar  implements Filter {




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = ((HttpServletResponse) servletResponse);
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);

        HttpSession session = httpRequest.getSession(false);

        if (session == null){
            httpResponse.sendRedirect("index.html?msg=Você precisa logar antes");
        }else{
            Boolean logado = (Boolean) session.getAttribute("logado");
            if (!logado || logado == null){
                httpResponse.sendRedirect("index.html?msg=Você precisa logar antes");
                return;
            }


        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
