package br.com.fiap;

import br.com.fiap.domain.entity.Area;
import br.com.fiap.domain.entity.Documento;
import br.com.fiap.domain.entity.TipoDocumento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

        EntityManager manager = factory.createEntityManager();

        //Area area = addArea(manager);

        //TipoDocumento tipoDocumento = addTipoDocumento(manager);

        Documento doc = new Documento().setNumero("123456").setValidade(LocalDate.now().plusYears(5)).setTipoDocumento(findById(1L, manager));

        manager.getTransaction().begin();
        manager.persist(doc);
        manager.getTransaction().commit();

        System.out.println(doc);

        manager.close();

        factory.close();
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