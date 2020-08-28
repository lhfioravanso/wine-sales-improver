package com.lhfioravanso.winesalesimprover.domain;

import java.math.BigDecimal;
import java.util.List;

public class Purchase {

    private String codigo;
    private String data;
    private String cliente;
    private BigDecimal valorTotal;
    private List<Item> itens;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = this.formatCpf(cliente);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public String getYear() {
        return this.data.split("-")[2];
    }

    private String formatCpf(String cpf) {

        StringBuilder formattedCpf = new StringBuilder(cpf);

        formattedCpf.replace(cpf.lastIndexOf('.'), cpf.lastIndexOf('.') + 1, "-");

        if(cpf.indexOf('.') == 4)
            return formattedCpf.substring(1);

       return formattedCpf.toString();
    }

}
