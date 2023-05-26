

public class Arrow {
    private int src ,dst;
    private int dstPin ;

    private boolean branch=false;
    
    private int b1dst=0,b2dst;
    private int b1Pin,b2Pin;

    public int getSrc() {
        return src;
    }
    public void setSrc(int src) {
        this.src = src;
    }

    public int getDst() {
        return dst;
    }
    public void setDst(int dst) {
        this.dst = dst;
    }

    public int getDstPin() {
        return dstPin;
    }
    public void setDstPin(int dstPin) {
        this.dstPin = dstPin;
    }

    public boolean isBranch() {
        return branch;
    }
    public void setBranch() {
        this.branch = true;
    }

    public int getB1dst() {
        return b1dst;
    }
    public void setB1dst(int b1dst) {
        this.b1dst = b1dst;
    }

    public int getB2dst() {
        return b2dst;
    }
    public void setB2dst(int b2dst) {
        this.b2dst = b2dst;
    }

    public int getB1Pin() {
        return b1Pin;
    }
    public void setB1Pin(int b1Pin) {
        this.b1Pin = b1Pin;
    }

    public int getB2Pin() {
        return b2Pin;
    }
    public void setB2Pin(int b2Pin) {
        this.b2Pin = b2Pin;
    }
    @Override
    public String toString() {
        return "Arrow [src=" + src + ", dst=" + dst + ", dstPin=" + dstPin + ", branch=" + branch + ", b1dst=" + b1dst
                + ", b2dst=" + b2dst + ", b1Pin=" + b1Pin + ", b2Pin=" + b2Pin + "]";
    }

    

}
