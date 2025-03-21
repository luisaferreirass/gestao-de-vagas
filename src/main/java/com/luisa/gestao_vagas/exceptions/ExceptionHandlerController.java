package com.luisa.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Trata exceção, antes de retornar ao usuário, entra aqui para a exceção ser tratada 
public class ExceptionHandlerController {

    private MessageSource messageSource;
    // Classe que ajuda a fazer o mapeamento da mensagem (buscar mensagens de erro)
    // MessageSource é um componente do Spring que permite buscar mensagens de erro internacionalizadas a partir de arquivos de propriedades

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource = messageSource;
        //Precisamos fazer isso para que, quando inicializarmos, o message source n fique nulo
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Quando der uma exceção desse tipo, cai nesse método
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorMessageDTO> dto = new ArrayList<>();



        e.getBindingResult().getFieldErrors().forEach(err -> {
            // O bindingResults retorna um objqto com os erros de validação
            // O FieldErrors retorna uma lista de erros específicos de campos 
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            // busca a mensagem de erro correspondente ao erro de validação, considerando o idioma do usuário (LocaleContextHolder.getLocale()).
            // Estamos pegando as mensagens de cada field dos erros

            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
            // Estamos criando o objeto do erro no formato que quermos (field, message)
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        // Criamos uma classe ResponseEntity 


    }



}
