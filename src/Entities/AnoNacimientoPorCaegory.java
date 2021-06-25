package Entities;

public class AnoNacimientoPorCaegory implements Comparable<AnoNacimientoPorCaegory> {
    private int anoNacimiento = 0;
    private String category;
    private int cantidadPersonas = 1;

    public AnoNacimientoPorCaegory(int anoNacimiento, String category) {
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
    public int compareTo(AnoNacimientoPorCaegory o) {
        return compare(this.getCantidadPersonas(),o.getCantidadPersonas());
    }
    public static int compare (float x, float y) {
        return Float.compare(x, y);
    }
}
