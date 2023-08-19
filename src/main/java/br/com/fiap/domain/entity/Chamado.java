package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CHAMADO")
public class Chamado {
    @Id
    @SequenceGenerator(name = "SQ_CHAMADO", sequenceName = "SQ_CHAMADO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CHAMADO")
    @Column(name = "ID_CHAMADO")
    private Long id;
    @Column(name = "TITULO_CHAMADO", nullable = false)
    private String titulo;
    @Column(name = "DS_CHAMADO", nullable = false)
    private String descricao;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA", foreignKey = @ForeignKey(name = "FK_AREA"), nullable = false)
    private Area area;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_SOLICITANTE", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_SOLICITANTE"), nullable = false)
    private Pessoa solicitante;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_ATENDENTE", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_ATENDENTE"))
    private Pessoa atendente;
    @Column(name = "DT_ABERTURA", nullable = false)
    private LocalDateTime abertura;
    @Column(name = "DT_INICIO")
    private LocalDateTime inicio;
    @Column(name = "DT_FECHAMENTO")
    private LocalDateTime fechamento;

    public Chamado() {
    }

    public Chamado(Long id, String titulo, String descricao, Area area, Pessoa solicitante, Pessoa atendente, LocalDateTime abertura, LocalDateTime inicio, LocalDateTime fechamento) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setArea(area);
        this.setSolicitante(solicitante);
        this.setAtendente(atendente);
        this.setAbertura(abertura);
        this.setInicio(inicio);
        this.setFechamento(fechamento);
    }

    public Long getId() {
        return id;
    }

    public Chamado setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Chamado setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Chamado setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Area getArea() {
        return area;
    }

    public Chamado setArea(Area area) {
        this.area = area;
        return this;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }

    public Chamado setSolicitante(Pessoa solicitante) {
        this.solicitante = solicitante;
        return this;
    }

    public Pessoa getAtendente() {
        return atendente;
    }

    public Chamado setAtendente(Pessoa atendente) {
        this.atendente = atendente;
        return this;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public Chamado setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
        return this;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public Chamado setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public Chamado setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
        return this;
    }

    @Override
    public String toString() {
        return "Chamado{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", area=" + area +
                ", solicitante=" + solicitante +
                ", atendente=" + atendente +
                ", abertura=" + abertura +
                ", inicio=" + inicio +
                ", fechamento=" + fechamento +
                '}';
    }
}
