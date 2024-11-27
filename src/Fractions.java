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
        String N = leerNumerador(numerador);
        String D = leerDenominador(denomirador, numerador);
        return N + " " + D;
    }


    //Numeradores
    // Los 20 primeros son especiales asi que debemos poner todos los casos
    //Para sacar los demas los dividiremos para sacer centenas decenas y unidades
    //diviendiendo entre 100 y 10 para sacarlos siendo los residuos de /10 son las unidades
    //los denomedaros son parecidos los 20 primeros todos los casos y a partir del vente se añade un è
    //siempre que sea en singular y en prural seria ens
    //cuando el numerador sea mayor a 1 seran en singular en el resto de casos sera prural y las potencian
    // de 10 son èsim

    public static int dividirNumeros (int numerador,int denominador) {
        int unidades;
        int decimas = 0;
        while (numerador >= 10) {
            numerador = numerador / 10;
            decimas++;

        }
        unidades = numerador % denominador;
        return unidades;
    }


    public static String leerNumerador(int numerador) {
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
                return "décim";
            }
            return "décims";
        }
        if (denominador == 11) {
            if (numerador == 1) {
                return "onzé";
            }
            return "onzens";
        }
        if (denominador == 12) {
            if (numerador == 1) {
                return "dotzé";
            }
            return "dotzens";
        }
        if (denominador == 13) {
            if (numerador == 1) {
                return "tretzé";
            }
            return "tretzens";
        }
        if (denominador == 14) {
            if (numerador == 1) {
                return "catorzé";
            }
            return "catorzens";
        }
        if (denominador == 15) {
            if (numerador == 1) {
                return "quienzé";
            }
            return "quinzens";
        }
        if (denominador == 16) {
            if (numerador == 1) {
                return "setzé";
            }
            return "setzens";
        }
        if (denominador == 17) {
            if (numerador == 1) {
                return "dissete";
            }
            return "dissetens";
        }
        if (denominador == 18) {
            if (numerador == 1) {
                return "divuité";
            }
            return "divuitens";
        }
        if (denominador == 19) {
            if (numerador == 1) {
                return "dinové";
            }
            return "dinovens";
        }
        return "";
    }
}

