import java.util.Scanner;

public class Fractions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que Fraccion quieres");
        String s = scanner.nextLine();
        toWords(s);
        System.out.println(toWords(s));

    }

    //Es la funcion llama funciones sirve para construir la frase
    public static String toWords(String s) {
        String[] partes = s.split("/");
        int numerador = Integer.parseInt(partes[0]);
        int denominador = Integer.parseInt(partes[1]);

        //Comprueba que los numeros no son iguales
        if (numerador == denominador) {
            return "Un";
        }

        //Comprueba que los denominador no sea uno especial que en este caso serian del 1 al 4
        if (denominador <= 4) {
            String N = leerNumeradorHasta10000(numerador);
            String D = leerDenominadorSiEsEspecial(denominador);
            D = pruralOSingularEspecial(D, numerador);
            String resultado = N + " " + D;
            resultado = pasarAMayusculaPrimeraPalabra(resultado);
            return resultado;
        }

        String N = leerNumeradorHasta10000(numerador);
        String D = leerDenominadorHasta10000(denominador, numerador);
        D = pluralOSingular(D, numerador, denominador);


        //Juntar numerador y denominador y pasar a mayuscula
        String resultado = N + " " + D;
        resultado = pasarAMayusculaPrimeraPalabra(resultado);
        return resultado;
    }

    //Coge la primera letra del de una combinacion y la devuelve para que este en mayuscula la primera letra
    public static String pasarAMayusculaPrimeraPalabra(String resultado) {
        return resultado.substring(0, 1).toUpperCase() + resultado.substring(1);
    }

    //Como el pluralOSingular dara problemas a largo plazo con los unidades decimals etc he decidido cortar por lo sano
    public static String pruralOSingularEspecial(String denominador, int numerador) {
        if (denominador.equals("mig") || denominador.equals("quart")) {
            if (numerador == 1) {
                return denominador;
            } else if (numerador > 1) {
                return denominador + "s";
            }
        } else {
            if (numerador == 1) {
                return denominador;
            } else if (numerador > 1) {
                return denominador + "os";
            }
        }
        return "";
    }

    public static String pluralOSingular(String denominador, int numerador, int denominador2) {
        if (denominador2 % 10 == 0 && esPoTenciaDeDiez(denominador2)) {
            if (numerador == 1) {
                return denominador; //No añadimos nada para el singular si es multiple de 10
            } else {
                return denominador + "s"; //Añadimos 's' para el plural si es multiple de 10
            }
        } else if (numerador == 1) {
            return denominador + "è"; //Añadimos 'e' para el singular
        } else if (numerador > 1 ) {
            return denominador + "ens"; //Añadimos 'ens' para el plural
        }
        return "";
    }

    public static boolean esPoTenciaDeDiez(int denominador) {
        if (denominador < 10) return false;
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return denominador == 1;
    }

    //Cuando le das un numero te lo tranforma en letras pero solo los 19 primeros y esto lo hace el numerador
    public static String leerNumeradorHasta19(int numerador) {
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
    static String leerNumeradorHasta100(int numerador) {
        if (numerador <= 20) return leerNumeradorHasta19(numerador);
        int decimales = numerador / 10;
        int unidades = numerador % 10;
        if (decimales == 2) {
            return "vint-i-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 3) {
            return "trenta-i-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 4) {
            return "quaranta-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 5) {
            return "cinquanta-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 6) {
            return "seixanta-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 7) {
            return "setanta-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 8) {
            return "vuitanta-" + leerNumeradorHasta19(unidades);
        } else if (decimales == 9) {
            return "noranta-" + leerNumeradorHasta19(unidades);
        }
        return "Numero incorrecto";
    }

    public static String leerNumeradorHasta1000(int numerador) {
        if (numerador < 100) return leerNumeradorHasta100(numerador);
        int centesima = numerador / 100;
        int decimales = numerador % 100;
        if (centesima == 1 && decimales == 0) {
            return "cent" + leerNumeradorHasta100(decimales);
        } else if (centesima == 1) {
            return "cent" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 2 && decimales == 0) {
            return "dos-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 2) {
            return "dos-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 3 && decimales == 0) {
            return "tres-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 3) {
            return "tres-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 4 && decimales == 0 ) {
            return "quatre-cents" +  leerNumeradorHasta100(decimales);
        } else if (centesima == 4 ) {
            return "quatre-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 5 && decimales == 0) {
            return "cinc-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 5 ) {
            return "cinc-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 6 && decimales == 0) {
            return "sis-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 6) {
            return "sis-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 7 && decimales == 0) {
            return "set-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 7) {
            return "set-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 8 && decimales == 0) {
            return "vuit-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 8) {
            return "vuit-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 9 && decimales == 0) {
            return "nou-cents" + leerNumeradorHasta100(decimales);
        } else if (centesima == 9) {
            return "nou-cents" + " " + leerNumeradorHasta100(decimales);
        } else if (centesima == 10 && decimales == 0) {
            return "mil" + leerNumeradorHasta100(decimales);
        } else if (centesima == 10) {
            return "mil" + " " + leerNumeradorHasta100(decimales);
        }
        return "";
    }

    public static String leerNumeradorHasta10000(int numerador) {
        if (numerador < 1000) return leerNumeradorHasta1000(numerador);
        int milesimas = numerador / 1000;
        int centesima = numerador % 1000;
        if (milesimas == 1 && centesima == 0) {
            return "mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 1) {
            return "mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 2 && centesima == 0) {
            return "dos mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 2) {
            return "dos mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 3 && centesima == 0) {
            return "tres mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 3) {
            return "tres mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 4 && centesima == 0 ) {
            return "quatre mil" +  leerNumeradorHasta1000(centesima);
        } else if (milesimas == 4 ) {
            return "quatre mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 5 && centesima == 0) {
            return "cinc mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 5 ) {
            return "cinc mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 6 && centesima == 0) {
            return "sis mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 6) {
            return "sis mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 7 && centesima == 0) {
            return "set mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 7) {
            return "set mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 8 && centesima == 0) {
            return "vuit mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 8) {
            return "vuit mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 9 && centesima == 0) {
            return "nou mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 9) {
            return "nou mil" + " " + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 10 && centesima == 0) {
            return "deu mil" + leerNumeradorHasta1000(centesima);
        } else if (milesimas == 10) {
            return "deu mil" + " " + leerNumeradorHasta1000(centesima);
        }
        return "";
    }

    public static String leerDenominadorSiEsEspecial(int denominador) {
        if (denominador == 1) {
            return "";
        } else if (denominador == 2) {
            return "mig";
        } else if (denominador == 3) {
            return "terç";
        } else if (denominador == 4) {
            return "quart";
        }
        return "";
    }

    //Cuando le das un numero te lo tranforma en letras pero solo los 19 primeros te transforma los denominadores.
    public static String leerDenominadorHasta19(int denominador) {
        if (denominador == 1) {
            return "un";
        } else if (denominador == 2) {
            return "dos";
        } else if (denominador == 3) {
            return "tres";
        } else if (denominador == 4) {
            return "quatr";
        } else if (denominador == 5) {
            return "cinqu";
        } else if (denominador == 6) {
            return "sis";
        } else if (denominador == 7) {
            return "set";
        } else if (denominador == 8) {
            return "vuit";
        } else if (denominador == 9) {
            return "nov";
        } else if (denominador == 10) {
            return "dècim";
        } else if (denominador == 11) {
            return "onz";
        } else if (denominador == 12) {
            return "dotz";
        } else if (denominador == 13) {
            return "tretz";
        } else if (denominador == 14) {
            return "catorz";
        } else if (denominador == 15) {
            return "quinz";
        } else if (denominador == 16) {
            return "setz";
        } else if (denominador == 17) {
            return "disset";
        } else if (denominador == 18) {
            return "divuit";
        } else if (denominador == 19) {
            return "dinov";
        } else if (denominador == 20) {
            return "vint";
        }
        return "";
    }

    //hace lo mismo que la anterior pero junta un prefijo dependiendo del numero de la variable decimales + un resultado
    //que coje de la funcion anterior con la variable unidades
    public static String leerDenominadorHasta100(int denominador) {
        if (denominador <= 20) return leerDenominadorHasta19(denominador);
        int decimales = denominador / 10;
        int unidades = denominador % 10;
        if (decimales == 2) {
            if (unidades == 0) {
                return "vint";
            }
            return "vint-i-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 3) {
            if (unidades == 0) {
                return "trent";
            }
            return "trenta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 4) {
            if (unidades == 0) {
                return "quarent";
            }
            return "quaranta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 5) {
            if (unidades == 0) {
                return "cinquant";
            }
            return "cinquanta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 6) {
            if (unidades == 0) {
                return "seixant";
            }
            return "seixanta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 7) {
            if (unidades == 0) {
                return "setant";
            }
            return "setanta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 8) {
            if (unidades == 0) {
                return "vuitant";
            }
            return "vuitanta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 9) {
            if (unidades == 0) {
                return "norant";
            }
            return "noranta-" + leerDenominadorHasta19(unidades);
        } else if (decimales == 10) {
            if (unidades == 0) {
                return "centèsim";
            }
            return "centesim-" + leerDenominadorHasta19(unidades);
        }
        return "";
    }

    public static String leerDenominadorHasta1000(int denominador, int numerador) {
        if (denominador <= 100) return leerDenominadorHasta100(denominador);
        int centesima = denominador / 100;
        int decimales = denominador % 100;
        if (esPoTenciaDeDiez(denominador)){
            return "mil·lèsim";
        }
        if (centesima == 1) {
            if (numerador == 1 && decimales == 0) {
                return "centèsim";
            }
            return "cent" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 2) {
            if (numerador == 1 && decimales == 0) {
                return "dos-centèsim";
            }
            return "dos-cents" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 3) {
            if (numerador == 1 && decimales == 0) {
                return "tres-centèsim";
            }
            return "tres-cents" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 4) {
            if (numerador == 1 && decimales == 0) {
                return "quatre-centèsim";
            }
            return "quatre-cents" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 5) {
            if (numerador == 1 && decimales == 0) {
                return "cinc-centèsim";
            }
            return "cinc-cents" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 6) {
            if (numerador == 1 && decimales == 0) {
                return "sis-centèsim";
            }
            return "sis-cents" + " " + leerDenominadorHasta100(decimales);
        } else if (centesima == 7) {
            if (numerador == 1 && decimales == 0) {
                return "set-centèsim";
            }
            return "set-cents" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 8) {
            if (numerador == 1 && decimales == 0) {
                return "vuit-centèsim";
            }
            return "vuit-cents" + " " + leerDenominadorHasta100(decimales);
        } else if (centesima == 9) {
            if (numerador == 1 && decimales == 0) {
                return "nou-centèsim";
            }
            return "nou-cents" + " " + leerDenominadorHasta100(decimales);
        }else if (centesima == 10) {
            if (numerador == 1 && decimales == 0) {
                return "mil·lèsim";
            }
            return "mil" + " " + leerDenominadorHasta100(decimales);
        }
        return "";
    }

    public static String leerDenominadorHasta10000(int denominador, int numerador) {
        if (denominador <= 1000 && denominador != 1) return leerDenominadorHasta1000(denominador, numerador);
        if (denominador == 1) {
            return "";
        }
        int milesimas = denominador / 1000;
        int centesimas = denominador % 1000;
        if (milesimas == 1) {
            if (numerador == 1 && centesimas == 0) {
                return "mil·lèsim";
            }
            return "mil" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }else if (milesimas == 2) {
            if (numerador == 1 && centesimas == 0) {
                return "dos mil·lèsim";
            }
            return "dos mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }else if (milesimas == 3) {
            if (numerador == 1 && centesimas == 0) {
                return "tres mil·lèsim";
            }
            return "tres mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }else if (milesimas == 4) {
            if (numerador == 1 && centesimas == 0) {
                return "quatre mil·lèsim";
            }
            return "quatre mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }else if (milesimas == 5) {
            if (numerador == 1 && centesimas == 0) {
                return "cinc mil·lèsim";
            }
            return "cinc mil·lèsim" + " " + leerDenominadorHasta10000(centesimas, numerador);
        }else if (milesimas == 6) {
            if (numerador == 1 && centesimas == 0) {
                return "sis mil·lèsim";
            }
            return "sis mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        } else if (milesimas == 7) {
            if (numerador == 1 && centesimas == 0) {
                return "set mil·lèsim";
            }
            return "set mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }else if (milesimas == 8) {
            if (numerador == 1 && centesimas == 0) {
                return "vuit mil·lèsim";
            }
            return "vuit mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        } else if (milesimas == 9) {
            if (numerador == 1 && centesimas == 0) {
                return "nou mil·lèsim";
            }
            return "nou mil·lèsim" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }else if (milesimas == 10) {
            if (numerador == 1 && centesimas == 0) {
                return "deu mil·lèsim";
            }
            return "deu mil" + " " + leerDenominadorHasta1000(centesimas, numerador);
        }
        return "";
    }



}