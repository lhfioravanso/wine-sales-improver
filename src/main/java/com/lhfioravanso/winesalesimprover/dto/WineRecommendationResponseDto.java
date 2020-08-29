package com.lhfioravanso.winesalesimprover.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class WineRecommendationResponseDto implements Serializable {

    private String produto;
    private String variedade;
    private String pais;
    private String categoria;
    private String safra;
    private BigDecimal preco;

    public WineRecommendationResponseDto(String produto, String variedade, String pais, String categoria, String safra, BigDecimal preco) {
        this.produto = produto;
        this.variedade = variedade;
        this.pais = pais;
        this.categoria = categoria;
        this.safra = safra;
        this.preco = preco;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getVariedade() {
        return variedade;
    }

    public void setVariedade(String variedade) {
        this.variedade = variedade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
