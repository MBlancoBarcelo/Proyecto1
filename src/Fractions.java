import java.util.Scanner;

public class Fractions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que Fraccion quieres");
        String s = scanner.nextLine() ;
        toWords(s);
        System.out.println(toWords(s));

    }

    //Es la funcion llama funciones sirve para construir la frase
    public static String toWords(String s) {
        String[] partes = s.split("/");
        int numerador = Integer.parseInt(partes[0]);
        int denomirador = Integer.parseInt(partes[1]);

        //Comprueba que los numeros no son iguales
        if (numerador == denomirador) {
            return "Un";
        }

        String N = leerNumeradorHasta1000(numerador);
        String D = leerDenominadorHasta1000(denomirador, numerador);
        D = pluralOSingular(D,numerador);


        // Juntar numberador y denominador y pasar a mayuscula
        String resultado = N + " " + D;
        resultado = pasarAMayusculaPimeraPalabra(resultado);
        return resultado;
    }

    //Coge la primera letra del de una combinacion y la devuelve para que este en mayuscula la primera letra
    public static String pasarAMayusculaPimeraPalabra(String resultado) {
        return resultado.substring(0,1).toUpperCase() + resultado.substring(1);
    }

    //Cuando le das un numero te lo tranforma en letras pero solo los 19 primeros y esto lo hace el numerador
    public static String leerNumeradorHastaEl19(int numerador) {
        if (numerador == 1) {
            return "un";
        } else if (numerador == 2) {
            return "dos";
        } else if (numerador == 3) {
            return "tres";
        } else if (numerador == 4) {
            return "quatre";
        } else if (numerador == 5) {
            return "cinc";
        } else if (numerador == 6) {
            return "sis";
        } else if (numerador == 7) {
            return "set";
        } else if (numerador == 8) {
            return "vuit";
        } else if (numerador == 9) {
            return "nou";
        } else if (numerador == 10) {
            return "deu";
        } else if (numerador == 11) {
            return "onze";
        } else if (numerador == 12) {
            return "dotze";
        } else if (numerador == 13) {
            return "tretze";
        } else if (numerador == 14) {
            return "catorze";
        } else if (numerador == 15) {
            return "quinze";
        } else if (numerador == 16) {
            return "setze";
        } else if (numerador == 17) {
            return "disset";
        } else if (numerador == 18) {
            return "divuit";
        } else if (numerador == 19) {
            return "dinou";
        } else if (numerador == 20) {
            return "vint";
        } else if (numerador == 0) {
            return "";
        }

        return "LA FRACCION NO ES VALIDA";
    }

    //hace lo mismo que la anterior pero junta un prefijo dependiendo del numero de la variable decimales + un resultado
    //que coje de la funcion anterior con la variable unidades
    public  static String leerNumeradorHasta100(int numerador) {
        if (numerador <= 20) return leerNumeradorHastaEl19(numerador);
        int decimales = numerador / 10;
        int unidades = numerador % 10;
        if (decimales == 2) {
            return "vint-i-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 3) {
            return "trenta-i-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 4) {
            return "quaranta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 5) {
            return "cinquanta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 6) {
            return "seixanta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 7) {
            return "setanta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 8) {
            return "vuitanta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales ==  9) {
            return "noranta-" + leerNumeradorHastaEl19(unidades);
        }
        return "Numero incorrecto";
    }

    public static String leerNumeradorHasta1000(int numerador) {
        if (numerador < 100) return leerNumeradorHasta100(numerador);
        int centesima = numerador / 100;
        int decimales = numerador % 100;
        if (centesima == 1 && decimales == 0){
            return "cent" +  leerNumeradorHasta100(decimales);
        } else if (centesima == 1) {
            return "cent" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 2 && decimales == 0) {
            return  "dos-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 2 ) {
            return "dos-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 3 ) {
            return "tres-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 4 ) {
            return "quatre-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 5) {
            return "cinc-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 6 ) {
            return "sis-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 7 ) {
            return "set-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 8 ) {
            return "vuit-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 9 ) {
            return "nou-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 10) {
            return "mil" + " " + leerNumeradorHasta100(decimales);
        }
        return "";
    }

    //Cuando le das un numero te lo tranforma en letras pero solo los 19 primeros te transforma los denominadores.

    public static String pluralOSingular (String denominador , int numerador ) {
        if (numerador == 1) {
            return denominador + "è";
        }else if (numerador > 1) {
            return denominador + "ens";
        }
    }

}

