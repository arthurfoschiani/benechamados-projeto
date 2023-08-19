package br.com.fiap;

import br.com.fiap.domain.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

        EntityManager manager = factory.createEntityManager();

        //Area area = addArea(manager);

        //TipoDocumento tipoDocumento = addTipoDocumento(manager);

        //Pessoa pessoa = addPessoa(manager);

        //Documento documento = addDocumento(manager);

        Chamado chamado = addChamado(manager);

        manager.close();

        factory.close();
    }

    private static Chamado addChamado(EntityManager manager) {
        String titulo = JOptionPane.showInputDialog("Titulo do chamado");

        String descricao = JOptionPane.showInputDialog("Descrição do chamado");

        List<Area> areas = manager.createQuery("FROM Area").getResultList();

        Area areaSelecionada = (Area) JOptionPane.showInputDialog(null, "Selecione a área", "Seleção da área", JOptionPane.QUESTION_MESSAGE, null, areas.toArray(), areas.get(0));

        List<Pessoa> pessoas = manager.createQuery("FROM Pessoa").getResultList();

        Pessoa solicitanteSelecionada = (Pessoa) JOptionPane.showInputDialog(null, "Selecione o solicitante", "Seleção do solicitante", JOptionPane.QUESTION_MESSAGE, null, pessoas.toArray(), pessoas.get(0));

        Pessoa atendenteSelecionada = (Pessoa) JOptionPane.showInputDialog(null, "Selecione o atendente", "Seleção do atendente", JOptionPane.QUESTION_MESSAGE, null, pessoas.toArray(), pessoas.get(0));

        Chamado chamado = new Chamado().setTitulo(titulo).setDescricao(descricao).setArea(areaSelecionada).setSolicitante(solicitanteSelecionada).setAtendente(atendenteSelecionada).setAbertura(LocalDateTime.now());

        manager.getTransaction().begin();
        manager.persist(chamado);
        manager.getTransaction().commit();

        return chamado;
    }

    private static Pessoa addPessoa(EntityManager manager) {
        String nome = JOptionPane.showInputDialog("Nome da pessoa");

        String nascimento = JOptionPane.showInputDialog("Data de Nascimento no formato DD/MM/AAAA");
        int dia = Integer.parseInt(nascimento.substring(0,2));
        int mes = Integer.parseInt(nascimento.substring(3,5));
        int ano = Integer.parseInt(nascimento.substring(6,10));

        Pessoa pessoa = new Pessoa().setNome(nome).setNascimento(LocalDate.of(ano, mes, dia));

        manager.getTransaction().begin();
        manager.persist(pessoa);
        manager.getTransaction().commit();

        return pessoa;
    }

    private static Documento addDocumento(EntityManager manager) {

        List<TipoDocumento> tipos = manager.createQuery("FROM TipoDocumento").getResultList();

        TipoDocumento tipoSelecionado = (TipoDocumento) JOptionPane.showInputDialog(null, "Selecione o tipo do documento", "Seleção do Tipo de Documento", JOptionPane.QUESTION_MESSAGE, null, tipos.toArray(), tipos.get(0));

        String numero = JOptionPane.showInputDialog("Número do documento");

        String validade = JOptionPane.showInputDialog("Data de Validade no formato DD/MM/AAAA");
        int dia = Integer.parseInt(validade.substring(0,2));
        int mes = Integer.parseInt(validade.substring(3,5));
        int ano = Integer.parseInt(validade.substring(6,10));

        List<Pessoa> pessoas = manager.createQuery("FROM Pessoa ").getResultList();

        Pessoa pessoaSelecionada = (Pessoa) JOptionPane.showInputDialog(null, "Selecione a pessoa", "Seleção de pessoas", JOptionPane.QUESTION_MESSAGE, null, pessoas.toArray(), pessoas.get(0));

        Documento doc = new Documento().setNumero(numero).setValidade(LocalDate.of(ano, mes, dia)).setTipoDocumento(tipoSelecionado).setPessoa(pessoaSelecionada);

        manager.getTransaction().begin();
        manager.persist(doc);
        manager.getTransaction().commit();

        System.out.println(doc);

        return doc;
    }

    static TipoDocumento findById(Long id, EntityManager manager) {
        return manager.find(TipoDocumento.class, id);
    }

    private static TipoDocumento addTipoDocumento(EntityManager manager) {
        boolean salvou = false;

        do {
            String nome = JOptionPane.showInputDialog("Tipo do documento: ");

            TipoDocumento tipo = new TipoDocumento().setNome(nome);

            try {
                manager.getTransaction().begin();
                manager.persist(tipo);
                manager.getTransaction().commit();
                salvou = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: \nNão foi possível salvar o tipo de documento " + e.getMessage());
            }
            return tipo;
        } while (salvou == false);
    }

    private static Area addArea(EntityManager manager) {
        boolean salvou = false;

        do {

            String nome = JOptionPane.showInputDialog("Nome da área: ");
            String descricao = JOptionPane.showInputDialog("Descrição da área: ");

            Area area = new Area().setNome(nome).setDescricao(descricao);

            try {
                manager.getTransaction().begin();
                manager.persist(area);
                manager.getTransaction().commit();

                System.out.println(area);
                salvou = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível salvar a área. Verifique se já existe uma área com esse nome." + e.getMessage());
            }
            return area;
        } while (salvou == false);
    }
}