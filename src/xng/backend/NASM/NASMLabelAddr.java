package xng.backend.NASM;

public class NASMLabelAddr extends NASMAddr{

    private String label;

    NASMLabelAddr(String _l){
        label = _l;
    }

    @Override
    public String toString() {
        return label;
    }
}
