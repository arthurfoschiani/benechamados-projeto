package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_DOCUMENTO")
public class Documento {
    @Id
    @SequenceGenerator(name = "SQ_DOCUMENTO", sequenceName = "SQ_DOCUMENTO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOCUMENTO")
    @Column(name = "ID_DOCUMENTO")
    private Long id;
    @Column(name = "NR_DOCUMENTO")
    private String numero;
    @Column(name = "DT_VALIDADE")
    private LocalDate validade;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_TP_DOCUMENTO", referencedColumnName = "ID_TP_DOCUMENTO", foreignKey = @ForeignKey(name = "FK_TP_DOCUMENTO"))
    private TipoDocumento tipoDocumento;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_PESSOA"))
    private Pessoa pessoa;

    public Documento() {
    }

    public Documento(Long id, String numero, LocalDate validade, TipoDocumento tipoDocumento, Pessoa pessoa) {
        this.setId(id);
        this.setNumero(numero);
        this.setValidade(validade);
        this.setTipoDocumento(tipoDocumento);
        this.setPessoa(pessoa);
    }

    public Long getId() {
        return id;
    }

    public Documento setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public Documento setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public Documento setValidade(LocalDate validade) {
        this.validade = validade;
        return this;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public Documento setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Documento setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", validade=" + validade +
                ", tipoDocumento=" + tipoDocumento +
                ", pessoa=" + pessoa +
                '}';
    }
}
