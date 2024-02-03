package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Enum.TipoDocumento;
import com.example.pruebatecnicaquind.Service.IClienteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private IClienteService iClienteService;

    @Test
    void createCliente_Success() {
        ClienteDto clienteDto = ClienteDto.builder()
                .nombre("string")
                .apellido("string")
                .email("string")
                .fechaNacimiento("2001-18-06")
                .tipoIdentificacion(TipoDocumento.CC)
                .build();

        when(iClienteService.createCliente(any(ClienteDto.class))).thenReturn(true);

        clienteController.createCliente(clienteDto);
    }

    @Test
    void createCliente_Failure() {
        ClienteDto clienteDto = ClienteDto.builder().build();

        when(iClienteService.createCliente(any(ClienteDto.class))).thenThrow(new RuntimeException(MessageAplication.ACCOUNTCANNOTCANCELLED));

        clienteController.createCliente(clienteDto);
    }

    @Test
    void updateCliente_Success() {
        ClienteDto clienteDto = ClienteDto.builder()
                .tipoIdentificacion(TipoDocumento.CC)
                .build();

        String numeroIdentificacion = "123456789";

        when(iClienteService.updateCliente(eq(numeroIdentificacion), any(ClienteDto.class))).thenReturn(true);

        clienteController.updateCliente(numeroIdentificacion, clienteDto);
    }

    @Test
    void updateCliente_NotFound() {
        ClienteDto clienteDto = ClienteDto.builder()
                .tipoIdentificacion(TipoDocumento.CC)
                .build();

        String numeroIdentificacion = "123456789";

        when(iClienteService.updateCliente(eq(numeroIdentificacion), any(ClienteDto.class))).thenThrow(new IllegalArgumentException(MessageAplication.ACCOUNTNOTFOUND));

        clienteController.updateCliente(numeroIdentificacion, clienteDto);
    }

    @Test
    void updateCliente_Exception() {
        ClienteDto clienteDto = ClienteDto.builder()
                .tipoIdentificacion(TipoDocumento.CC)
                .build();

        String numeroIdentificacion = "123456789";

        when(iClienteService.updateCliente(eq(numeroIdentificacion), any(ClienteDto.class))).thenThrow(new RuntimeException(MessageAplication.ACCOUNTCANCELLED));

        clienteController.updateCliente(numeroIdentificacion, clienteDto);
    }

    @Test
    void deleteCliente_Success() {
        String numeroIdentificacion = "123456789";

        when(iClienteService.deleteCliente(eq(numeroIdentificacion))).thenReturn(MessageAplication.ACCOUNTDELETED);

        clienteController.deleteCliente(numeroIdentificacion);
    }

    @Test
    void deleteCliente_NotFound() {
        String numeroIdentificacion = "123456789";

        when(iClienteService.deleteCliente(eq(numeroIdentificacion))).thenThrow(new IllegalArgumentException(MessageAplication.ACCOUNTNOTFOUND));

        clienteController.deleteCliente(numeroIdentificacion);
    }

    @Test
    void deleteCliente_Exception() {
        String numeroIdentificacion = "123456789";

        when(iClienteService.deleteCliente(eq(numeroIdentificacion))).thenThrow(new RuntimeException(MessageAplication.ACCOUNTCANCELLED));

        clienteController.deleteCliente(numeroIdentificacion);
    }
}
