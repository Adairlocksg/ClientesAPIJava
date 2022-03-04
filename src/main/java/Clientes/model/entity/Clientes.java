package Clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = "cnpj"))
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser informado para o Aluno!")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "dataCadastro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(length = 100)
    private String endereco;

    @Column(length = 20)
    private String cnpj;

    @Column(length = 13)
    private String telefone;

    @PrePersist
    public void prePersiste(){
        setDataCadastro(LocalDate.now());
    }
}
