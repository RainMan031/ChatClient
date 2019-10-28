package com.example.chatclient;


;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensagem {

    private String deAlguem, mensagem;
    private Boolean deMim;
    private Date data;


    public Mensagem(String deAlguem, String mensagem, Boolean deMim,Date data){
        this.data=data;
        this.deAlguem=deAlguem;
        this.deMim=deMim;
        this.mensagem=mensagem;
    }

    public String getDeAlguem() {
        return deAlguem;
    }

    public void setDeAlguem(String deAlguem) {
        this.deAlguem = deAlguem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getDeMim() {
        return deMim;
    }

    public void setDeMim(Boolean deMim) {
        this.deMim = deMim;
    }

    public String getData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        return sdf.format(data);

    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm ");

        return sdf.format(data);

    }

    public void setData(Date data) {
        this.data = data;
    }
}
