package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El tipo de identificación no puede estar vacío")
    private String tipoIdentificacion;

    @NotNull(message = "El número de identificación no puede estar vacío")
    private Long numeroIdentificacion;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Length(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @Length(min = 2, message = "El apellido debe tener al menos 2 caracteres")
    private String apellido;

    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;

    private String fechaNacimiento;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Producto> productos;

//MAPPER
    public static Cliente fromClienteDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setFechaCreacion(clienteDTO.getFechaCreacion());
        cliente.setFechaModificacion(clienteDTO.getFechaModificacion());

        return cliente;
    }
}
