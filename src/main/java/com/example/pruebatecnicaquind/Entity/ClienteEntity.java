package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Enum.TipoDocumento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


/***
 * class representing the entity cliente in the database
 *
 */
@Entity
@Table(name = "cliente")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion")
    private TipoDocumento tipoIdentificacion;

    @NotNull(message = "El número de identificación no puede estar vacío")
    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Length(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    @Column(name = "nombre_cliente")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @Length(min = 2, message = "El apellido debe tener al menos 2 caracteres")
    @Column(name = "apellido_cliente")
    private String apellido;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "El formato del email no es válido")
    @Column(name = "email")
    private String email;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @OneToMany(mappedBy = "clienteEntity", cascade = CascadeType.ALL)
    private List<ProductoEntity> productoEntity;
}
