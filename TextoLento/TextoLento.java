package TextoLento;
public class TextoLento {

    public static void exibeTextoLento(String texto, int atraso) {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            try {
                Thread.sleep(atraso);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


