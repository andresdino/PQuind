package com.example.pruebatecnicaquind.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El tipo de identificación no puede estar vacío")
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @NotNull(message = "El número de identificación no puede estar vacío")
    @Column(name = "numero_identificacion")
    private Long numeroIdentificacion;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Length(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    @Column(name = "nombre_cliente")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @Length(min = 2, message = "El apellido debe tener al menos 2 caracteres")
    @Column(name = "apellido_cliente")
    private String apellido;

    @Email(message = "El correo electrónico debe tener un formato válido")
    @Column(name = "email")
    private String email;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @OneToMany(mappedBy = "clienteEntity", cascade = CascadeType.ALL)
    private List<ProductoEntity> productoEntities;
}
