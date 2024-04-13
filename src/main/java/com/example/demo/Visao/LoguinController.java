package com.example.demo.Visao;

import com.example.demo.Aplicacao.Pessoa;
import com.example.demo.Persistencia.PessoaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class LoguinController {


    @RequestMapping(value = "/logar", method= RequestMethod.POST)
    public void doLogin(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        var writter = response.getWriter();
        HttpSession session = request.getSession(false);
        Pessoa p = new Pessoa(nome,senha);
        if (Pessoa.autenticar(p)){
            session = request.getSession();
            session.setAttribute("logado", true);
            session.setAttribute("user",p);
            response.sendRedirect("/lista");
        }else {
            if(session != null){
                session.invalidate();
                writter.println("nome ou senha incorretos");
                return;
            }
            writter.println("nome ou senha incorretos");


        }


    }
    @RequestMapping(value = "/cadastrar" , method = RequestMethod.POST)
    public void doCadastro(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        var writter = response.getWriter();
        //writter.println("Pâo de BATATA");
        Pessoa p = new Pessoa(nome,senha);
        Pessoa.cadastrar(p);
        Pessoa teste = PessoaDAO.buscar(p);
        if(teste != null){

            writter.println("Cadastro Realizado com Sucesso");
            response.sendRedirect("index.html");

        }else {
            writter.println("Cadastro Invalido");
        }
    }

    @GetMapping(value ={"/lista","Lista.html"} )
    public String Listagem(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException{

        ArrayList<Pessoa> lista = new ArrayList<>();
        lista = Pessoa.Listar();
        model.addAttribute("pessoas",lista);

        return "Lista";

    }
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public void Editar(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable Long id) throws IOException,ServletException{
        Pessoa p = PessoaDAO.buscar(id);

    }
    @GetMapping(value ="/excluir/{id}" )
    public String GetPageAPagar(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable long id) throws ServletException, IOException{
        model.addAttribute("id", id);
        Pessoa p=PessoaDAO.buscar(id);
        model.addAttribute("pessoa",p);
        return "Apagar";
    }

    @PostMapping  (value = "/excluir/{id}" )
    public void  Apagar(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) throws IOException, ServletException {
        Pessoa p = PessoaDAO.buscar(id);
        System.out.println(id);
        PessoaDAO.Deletar(p);
        // Envie um código de status HTTP 302 Found para redirecionar o usuário para outra página


    }


    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String editarPage(@PathVariable Integer id, Model model){
        model.addAttribute("id", id);
        Pessoa p=PessoaDAO.buscar(id);
        model.addAttribute("pessoa",p);
        return "Edit";
    }


}
