import java.util.Scanner;

public class Fractions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que Fraccion quieres");
        String s = scanner.nextLine() ;
        toWords(s);
        System.out.println(toWords(s));

    }


    public static String toWords(String s) {
        String[] partes = s.split("/");
        int numerador = Integer.parseInt(partes[0]);
        int denomirador = Integer.parseInt(partes[1]);


        String N = numeradorHasta100(numerador);
        String D = leerDenominador(denomirador, numerador);

        if (numerador == denomirador) {
            return "Un";
        }
        if (pasarAUnidadesNumerador(numerador) > 19) {
            String N1 = leerNumeradorHastaEl19(numerador);
        }

        // Juntar numberador y denominador y pasar a mayuscula
        String resultado = N + " " + D;
        resultado = pasarAMayusculaPimeraPalabra(resultado);
        return resultado;
    }

    public static String pasarAMayusculaPimeraPalabra(String resultado) {
        String cambio = resultado.substring(0,1).toUpperCase() + resultado.substring(1) ;
        return cambio;
    }

    public  static String numeradorHasta100(int numerador) {
        if (numerador < 20) return leerNumeradorHastaEl19(numerador);
        int decimales = numerador / 100;
        int unidades = numerador % 100;
        if (decimales == 2) {
            return "vint-i-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 3) {
            return "trenta-i-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 4) {
            return "quaranta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 5) {
            return "cinquanta-" + leerNumeradorHastaEl19(unidades);
        } else if (decimales == 6) {

        }
    }


    //Numeradores
    // Los 20 primeros son especiales asi que debemos poner todos los casos
    //Para sacar los demas los dividiremos para sacer centenas decenas y unidades
    //diviendiendo entre 100 y 10 para sacarlos siendo los residuos de /10 son las unidades
    //los denomedaros son parecidos los 20 primeros todos los casos y a partir del vente se añade un è
    //siempre que sea en singular y en prural seria ens
    //cuando el numerador sea mayor a 1 seran en singular en el resto de casos sera prural y las potencian
    // de 10 son èsim

    public static int pasarAUnidadesNumerador (int numerador) {
        int unidades = numerador % 10;
        return unidades;

    }

    public static String leerNumeradorHastaEl19(int numerador) {
        if (numerador == 1) {
            return "un";
        }
        if (numerador == 2) {
            return "dos";
        }
        if (numerador == 3) {
            return "tres";
        }
        if (numerador == 4) {
            return "quatre";
        }
        if (numerador == 5) {
            return "cinc";
        }
        if (numerador == 6) {
            return "sis";
        }
        if (numerador == 7) {
            return "set";
        }
        if (numerador == 8) {
            return "vuit";
        }
        if (numerador == 9) {
            return "nou";
        }
        if (numerador == 10) {
            return "deu";
        }
        if (numerador == 11) {
            return "onze";
        }
        if (numerador == 12) {
            return "dotze";
        }
        if (numerador == 13) {
            return "tretze";
        }
        if (numerador == 14) {
            return "catorze";
        }
        if (numerador == 15) {
            return "quinze";
        }
        if (numerador == 16) {
            return "setze";
        }
        if (numerador == 17) {
            return "disset";
        }
        if (numerador == 18) {
            return "divuit";
        }
        if (numerador == 19) {
            return "dinou";
        }

        return "LA FRACCION NO ES VALIDA";
    }


    public static String leerDenominador(int denominador, int numerador) {
        if (denominador == 1) {
            if (numerador == 1) {
                return "";
            }
            return "";
        }
        if (denominador == 2) {
            if (numerador == 1) {
                return "mig";
            }
            return "dossens";
        }
        if (denominador == 3) {
            if (numerador == 1) {
                return "terç";
            }
            return "tercens";
        }
        if (denominador == 4) {
            if (numerador == 1) {
                return "quart";
            }
            return "quarts";
        }
        if (denominador == 5) {
            if (numerador == 1) {
                return "cinquè";
            }
           return "cinquens";
        }
        if (denominador == 6) {
            if (numerador == 1) {
                return "sisè";
            }
            return "sisens";
        }
        if (denominador == 7) {
            if (numerador == 1) {
                return "setè";
            }
            return "setens";
        }
        if (denominador == 8) {
            if (numerador == 1) {
                return "vuitè";
            }
            return "vuitens";
        }
        if (denominador == 9) {
            if (numerador == 1) {
                return "novè";
            }
            return "novens";
        }
        if (denominador == 10) {
            if (numerador == 1) {
                return "dècim";
            }
            return "dècims";
        }
        if (denominador == 11) {
            if (numerador == 1) {
                return "onzè";
            }
            return "onzens";
        }
        if (denominador == 12) {
            if (numerador == 1) {
                return "dotzè";
            }
            return "dotzens";
        }
        if (denominador == 13) {
            if (numerador == 1) {
                return "tretzè";
            }
            return "tretzens";
        }
        if (denominador == 14) {
            if (numerador == 1) {
                return "catorzè";
            }
            return "catorzens";
        }
        if (denominador == 15) {
            if (numerador == 1) {
                return "quienzè";
            }
            return "quinzens";
        }
        if (denominador == 16) {
            if (numerador == 1) {
                return "setzè";
            }
            return "setzens";
        }
        if (denominador == 17) {
            if (numerador == 1) {
                return "dissetè";
            }
            return "dissetens";
        }
        if (denominador == 18) {
            if (numerador == 1) {
                return "divuitè";
            }
            return "divuitens";
        }
        if (denominador == 19) {
            if (numerador == 1) {
                return "dinovè";
            }
            return "dinovens";
        }
        return "";
    }
}

