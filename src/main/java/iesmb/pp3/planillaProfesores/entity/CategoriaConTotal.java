package iesmb.pp3.planillaProfesores.entity;

public class CategoriaConTotal {
    private Categoria  categoria;
    private String totalPorCategoria;

    public CategoriaConTotal() {
    }

    public CategoriaConTotal(Categoria categoria, String totalPorCategoria) {
        this.categoria = categoria;
        this.totalPorCategoria = totalPorCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTotalPorCategoria() {
        return totalPorCategoria;
    }

    public void setTotalPorCategoria(String totalPorCategoria) {
        this.totalPorCategoria = totalPorCategoria;
    }
}
