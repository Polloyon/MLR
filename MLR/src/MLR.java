
public class MLR {
	private class DataSet {
        public int n;
        private final double[] x1;
        private final double[] x2;
        private final double[] y;
        public double sumX_1 = 0;
        public double sumX_12 = 0;
        public double sumX_1X_2 = 0;
        public double sumX_1Y = 0;
        public double sumX_2 = 0;
        public double sumX_22 = 0;
        public double sumX_2Y = 0;
        public double sumY = 0;

        public DataSet() {
            this.n = 17;
            this.x1 = new double[]{41.9, 43.4, 43.9, 44.5, 47.3, 47.5, 47.9, 50.2, 52.8, 53.2, 56.7, 57.0, 63.5, 65.3, 71.1, 77.0, 77.8};
            this.x2 = new double[]{29.1, 29.3, 29.5, 29.7, 29.9, 30.3, 30.5, 30.7, 30.8, 30.9, 31.5, 31.7, 31.9, 32.0, 32.1, 32.5, 32.9};
            this.y = new double[]{251.3, 251.3, 248.3, 267.5, 273.0, 276.5, 270.3, 274.9, 285.0, 290.0, 297.0, 302.5, 304.5, 309.3, 321.7, 330.7, 349.0};
            for (int i = 0; i < this.n; i++) {
                sumX_1 = sumX_1 + (this.x1[i]);
                sumX_12 = sumX_12 + (this.x1[i]*this.x1[i]);
                sumX_1X_2 = sumX_1X_2 + (this.x1[i]*this.x2[i]);
                sumX_1Y = sumX_1Y + (this.x1[i]*this.y[i]);
                sumX_2 = sumX_2 + (this.x2[i]);
                sumX_22 = sumX_22 + (this.x2[i]*this.x2[i]);
                sumX_2Y = sumX_2Y + (this.x2[i]*this.y[i]);
                sumY = sumY + (this.y[i]);
            }
        }
    }
    
    private DataSet ds;
    public double beta0;
    public double beta1;
    public double beta2;
    private final double dSystem;
    
    
    public MLR() {
        this.ds = new DataSet();
        dSystem = (ds.n*ds.sumX_12*ds.sumX_22) + 2*(ds.sumX_1*ds.sumX_1X_2*ds.sumX_2) - (ds.sumX_12*ds.sumX_2*ds.sumX_2) - (ds.n*ds.sumX_1X_2*ds.sumX_1X_2) - (ds.sumX_1*ds.sumX_1*ds.sumX_22);
        beta0 = this.dBeta0()/this.dSystem;
        beta1 = this.dBeta1()/this.dSystem;
        beta2 = this.dBeta2()/this.dSystem;
    }
    
    private double dBeta0(){
        return (ds.sumX_12*ds.sumX_22*ds.sumY) + (ds.sumX_1*ds.sumX_1X_2*ds.sumX_2Y) + (ds.sumX_1X_2*ds.sumX_2*ds.sumX_1Y) - (ds.sumX_12*ds.sumX_2*ds.sumX_2Y) - (ds.sumX_1X_2*ds.sumX_1X_2*ds.sumY) - (ds.sumX_1*ds.sumX_22*ds.sumX_1Y);
    }
    
    private double dBeta1(){
        return (ds.n*ds.sumX_22*ds.sumX_1Y)+(ds.sumX_1X_2*ds.sumX_2*ds.sumY) + (ds.sumX_1*ds.sumX_2*ds.sumX_2Y) - (ds.sumX_2*ds.sumX_2*ds.sumX_1Y) - (ds.n*ds.sumX_1X_2*ds.sumX_2Y) - (ds.sumX_1*ds.sumX_22*ds.sumY);
    }

    private double dBeta2(){
        return (ds.n*ds.sumX_12*ds.sumX_2Y) + (ds.sumX_1*ds.sumX_2*ds.sumX_1Y) + (ds.sumX_1*ds.sumX_1X_2*ds.sumY) - (ds.sumX_12*ds.sumX_2*ds.sumY) - (ds.n*ds.sumX_1X_2*ds.sumX_1Y) - (ds.sumX_1*ds.sumX_1*ds.sumX_2Y);
    }
    
    public void printRegressionEquation(){
        String patron = "y = %.2f + %.2fx_1 + %.2fx_2";
        System.out.println(String.format(patron, beta0, beta1, beta2));
    }
    
    public void predictY(double x1, double x2){
        System.out.println("Factor 1 value: "+String.valueOf(x1));
        System.out.println("Factor 2 value: "+String.valueOf(x2));
        System.out.println("Prediction: y = "+String.valueOf(beta0+beta1*x1+beta2*x2));
    }
}
