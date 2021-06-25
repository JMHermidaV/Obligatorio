package Entities;

public class AnoNacimientoPorCategory implements Comparable<AnoNacimientoPorCategory> {
    private int anoNacimiento = 0;
    private String category;
    private int cantidadPersonas = 1;

    public AnoNacimientoPorCategory(int anoNacimiento, String category) {
        this.anoNacimiento = anoNacimiento;
        this.category = category;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public String getCategory() {
        return category;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas() {
        this.cantidadPersonas += 1;
    }

    @Override
    public int compareTo(AnoNacimientoPorCategory o) {
        return compare(this.getCantidadPersonas(),o.getCantidadPersonas());
    }
    public static int compare (float x, float y) {
        return Float.compare(x, y);
    }
}
