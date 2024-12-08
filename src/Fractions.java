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
            String N = leerNumeradorHasta1000000(numerador);
            String D = leerDenominadorSiEsEspecial(denominador);
            D = pruralOSingularEspecial(D, numerador);
            String resultado = N + " " + D;
            resultado = pasarAMayusculaPrimeraPalabra(resultado);
            return resultado;
        }

        String N = leerNumeradorHasta1000000(numerador);
        String D = leerDenominadorHasta100000(denominador, numerador);
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

    public static boolean esPotenciaDeDiez(int denominador) {
        if (denominador < 10) return false;
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return denominador == 1;
    }

    public static String manejarPotenciaDeDiez(String denominador, int numerador) {
        if (numerador == 1) {
            return denominador; // No añadimos nada para el singular si es múltiple de 10
        } else {
            return denominador + "s"; // Añadimos 's' para el plural si es múltiple de 10
        }
    }

    public static String pluralOSingular(String denominador, int numerador, int denominador2) {
        if (esPotenciaDeDiez(denominador2)) {
            return manejarPotenciaDeDiez(denominador, numerador); // Redirigimos a la nueva función
        } else if (numerador == 1) {
            return denominador + "è"; // Añadimos 'è' para el singular en otros casos
        } else if (numerador > 1) {
            return denominador + "ens"; // Añadimos 'ens' para el plural en otros casos
        }
        return "";
    }


    //Cuando le das un numero te lo tranforma en letras pero solo los 19 primeros y esto lo hace el numerador
    public static String leerNumeradorHasta19(int numerador) {
        return switch (numerador) {
            case 1 -> "un";
            case 2 -> "dos";
            case 3 -> "tres";
            case 4 -> "quatre";
            case 5 -> "cinc";
            case 6 -> "sis";
            case 7 -> "set";
            case 8 -> "vuit";
            case 9 -> "nou";
            case 10 -> "deu";
            case 11 -> "onze";
            case 12 -> "dotze";
            case 13 -> "tretze";
            case 14 -> "catorze";
            case 15 -> "quinze";
            case 16 -> "setze";
            case 17 -> "disset";
            case 18 -> "divuit";
            case 19 -> "dinou";
            case 20 -> "vint";
            case 0 -> "";
            default -> "LA FRACCION NO ES VALIDA";
        };
    }

    static String leerNumeradorHasta100(int numerador) {
        if (numerador <= 20) return leerNumeradorHasta19(numerador);
        int decimales = numerador / 10;
        int unidades = numerador % 10;
        return switch (decimales) {
            case 2 -> "vint-i-" + leerNumeradorHasta19(unidades);
            case 3 -> "trenta-" + leerNumeradorHasta19(unidades);
            case 4 -> "quaranta-" + leerNumeradorHasta19(unidades);
            case 5 -> "cinquanta-" + leerNumeradorHasta19(unidades);
            case 6 -> "seixanta-" + leerNumeradorHasta19(unidades);
            case 7 -> "setanta-" + leerNumeradorHasta19(unidades);
            case 8 -> "vuitanta-" + leerNumeradorHasta19(unidades);
            case 9 -> "noranta-" + leerNumeradorHasta19(unidades);
            default -> "Numero incorrecto";
        };
    }

    public static String leerNumeradorHasta1000(int numerador) {
        if (numerador < 100) return leerNumeradorHasta100(numerador);
        int centesima = numerador / 100;
        int decimales = numerador % 100;
        return switch (centesima) {
            case 1 -> "cent" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 2 -> "dos-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 3 -> "tres-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 4 -> "quatre-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 5 -> "cinc-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 6 -> "sis-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 7 -> "set-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 8 -> "vuit-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            case 9 -> "nou-cents" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            default -> "";
        };
    }

    public static String leerNumeradorHasta20000(int numerador) {
        if (numerador < 1000) return leerNumeradorHasta1000(numerador);
        int milesimas = numerador / 1000;
        if (milesimas > 20) {
            int separador = numerador % 10000;
            milesimas = separador / 1000;
        }
        int centesima = numerador % 1000;
        return switch (milesimas) {
            case 1 -> "mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 2 -> "dos mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 3 -> "tres mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 4 -> "quatre mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 5 -> "cinc mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 6 -> "sis mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 7 -> "set mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 8 -> "vuit mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 9 -> "nou mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 10 -> "deu mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 11 -> "onze mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 12 -> "dotze mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 13 -> "trezte mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 14 -> "catorze mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 15 -> "quinze mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 16 -> "setze mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 17 -> "desset mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 18 -> "divuit mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 19 -> "denou mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            case 20 -> "vint mil" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000(centesima));
            default -> " " + leerNumeradorHasta1000(centesima);
        };
    }

    public static String leerNumeradorHasta100000(int numerador) {
        if (numerador < 20000) return leerNumeradorHasta20000(numerador);
        int milesimas = numerador / 10000;
        int centesima = numerador % 10000;
        return switch (milesimas) {
            case 2 -> "vint" + (centesima == 0 ? "" : "-i-" + leerNumeradorHasta20000(numerador));
            case 3 -> "trenta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 4 -> "quaranta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 5 -> "cinquanta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 6 -> "seixanta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 7 -> "setanta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 8 -> "vuitanta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 9 -> "noranta" + (centesima == 0 ? "" : "-" + leerNumeradorHasta20000(numerador));
            case 10 -> "cent" + (centesima == 0 ? "" : " " + leerNumeradorHasta20000(numerador));
            default ->  "";
        };
    }

    public static String leerNumeradorHasta1000000(int numerador) {
        if (numerador < 100000) return leerNumeradorHasta100000(numerador);
        int milesimas = numerador / 100000;
        int centesima = numerador % 100000;
        return switch (milesimas) {
            case 1 -> "un milliò" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 2 -> "dos millions" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 3 -> "tres millions" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 4 -> "quatre milliò" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 5 -> "cinque millions" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 6 -> "sis millions" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 7 -> "set milliò" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 8 -> "vuit millions" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            case 9 -> "nou millions" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            default -> "";
        };
    }

    public static String leerDenominadorSiEsEspecial(int denominador) {
        return switch (denominador) {
            case 2 -> "mig";
            case 3 -> "terç";
            case 4 -> "quart";
            default -> "";
        };
    }

    public static String leerDenominadorHasta19(int denominador) {
        return switch (denominador) {
            case 1 -> "un";
            case 2 -> "dos";
            case 3 -> "tres";
            case 4 -> "quatr";
            case 5 -> "cinqu";
            case 6 -> "sis";
            case 7 -> "set";
            case 8 -> "vuit";
            case 9 -> "nov";
            case 10 -> "dècim";
            case 11 -> "onz";
            case 12 -> "dotz";
            case 13 -> "tretz";
            case 14 -> "catorz";
            case 15 -> "quinz";
            case 16 -> "setz";
            case 17 -> "disset";
            case 18 -> "divuit";
            case 19 -> "dinov";
            case 20 -> "vint";
            default -> "";
        };
    }

    public static String leerDenominadorHasta100(int denominador) {
        if (denominador <= 20) return leerDenominadorHasta19(denominador);
        int decimales = denominador / 10;
        int unidades = denominador % 10;
        return switch (decimales) {
            case 2 -> unidades == 0 ? "vint" : "vint-i-" + leerDenominadorHasta19(unidades);
            case 3 -> unidades == 0 ? "trent" : "trenta-" + leerDenominadorHasta19(unidades);
            case 4 -> unidades == 0 ? "quarent" : "quaranta-" + leerDenominadorHasta19(unidades);
            case 5 -> unidades == 0 ? "cinquant" : "cinquanta-" + leerDenominadorHasta19(unidades);
            case 6 -> unidades == 0 ? "seixant" : "seixanta-" + leerDenominadorHasta19(unidades);
            case 7 -> unidades == 0 ? "setant" : "setanta-" + leerDenominadorHasta19(unidades);
            case 8 -> unidades == 0 ? "vuitant" : "vuitanta-" + leerDenominadorHasta19(unidades);
            case 9 -> unidades == 0 ? "norant" : "noranta-" + leerDenominadorHasta19(unidades);
            case 10 -> unidades == 0 ? "centèsim" : "centesim-" + leerDenominadorHasta19(unidades);
            default -> "";
        };
    }

    public static String leerDenominadorHasta1000(int denominador, int numerador) {
        if (denominador <= 100) return leerDenominadorHasta100(denominador);
        int centesima = denominador / 100;
        int decimales = denominador % 100;
        if (esPotenciaDeDiez(denominador)) {
            return "mil·lèsim";
        }
        return switch (centesima) {
            case 1 -> (numerador == 1 && decimales == 0) ? "centèsim" : "cent " + leerDenominadorHasta100(decimales);
            case 2 -> (numerador == 1 && decimales == 0) ? "dos-centèsim" : "dos-cents " + leerDenominadorHasta100(decimales);
            case 3 -> (numerador == 1 && decimales == 0) ? "tres-centèsim" : "tres-cents " + leerDenominadorHasta100(decimales);
            case 4 -> (numerador == 1 && decimales == 0) ? "quatre-centèsim" : "quatre-cents " + leerDenominadorHasta100(decimales);
            case 5 -> (numerador == 1 && decimales == 0) ? "cinc-centèsim" : "cinc-cents " + leerDenominadorHasta100(decimales);
            case 6 -> (numerador == 1 && decimales == 0) ? "sis-centèsim" : "sis-cents " + leerDenominadorHasta100(decimales);
            case 7 -> (numerador == 1 && decimales == 0) ? "set-centèsim" : "set-cents " + leerDenominadorHasta100(decimales);
            case 8 -> (numerador == 1 && decimales == 0) ? "vuit-centèsim" : "vuit-cents " + leerDenominadorHasta100(decimales);
            case 9 -> (numerador == 1 && decimales == 0) ? "nou-centèsim" : "nou-cents " + leerDenominadorHasta100(decimales);
            case 10 -> (numerador == 1 && decimales == 0) ? "mil·lèsim" : "mil " + leerDenominadorHasta100(decimales);
            default -> "";
        };
    }

    public static String leerDenominadorHasta20000(int denominador, int numerador) {
        if (denominador <= 1000 && denominador != 1) return leerDenominadorHasta1000(denominador, numerador);
        if (denominador == 1) {
            return "";
        }
        int milesimas = denominador / 1000;
        if (milesimas > 20) {
            int separador = denominador % 10000;
            milesimas = separador / 1000;
        }
        int centesimas = denominador % 1000;
        return switch (milesimas) {
            case 1 -> (numerador == 1 && centesimas == 0) ? "mil·lèsim" : "mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 2 -> (numerador == 1 && centesimas == 0) ? "dos mil·lèsim" : "dos mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 3 -> (numerador == 1 && centesimas == 0) ? "tres mil·lèsim" : "tres mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 4 -> (numerador == 1 && centesimas == 0) ? "quatre mil·lèsim" : "quatre mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 5 -> (numerador == 1 && centesimas == 0) ? "cinc mil·lèsim" : "cinc mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 6 -> (numerador == 1 && centesimas == 0) ? "sis mil·lèsim" : "sis mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 7 -> (numerador == 1 && centesimas == 0) ? "set mil·lèsim" : "set mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 8 -> (numerador == 1 && centesimas == 0) ? "vuit mil·lèsim" : "vuit mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 9 -> (numerador == 1 && centesimas == 0) ? "nou mil·lèsim" : "nou mil·lèsim " + leerDenominadorHasta1000(centesimas, numerador);
            case 10 -> (numerador == 1 && centesimas == 0) ? "deu mil·lèsim" : "deu mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 11 -> (numerador == 1 && centesimas == 0) ? "onze mil·lèsim" : "onze mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 12 -> (numerador == 1 && centesimas == 0) ? "dotze mil·lèsim" : "dotze mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 13 -> (numerador == 1 && centesimas == 0) ? "trezte mil·lèsim" : "trezte mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 14 -> (numerador == 1 && centesimas == 0) ? "catorze mil·lèsim" : "catorze mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 15 -> (numerador == 1 && centesimas == 0) ? "quinze mil·lèsim" : "quinze mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 16 -> (numerador == 1 && centesimas == 0) ? "setze mil·lèsim" : "setze mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 17 -> (numerador == 1 && centesimas == 0) ? "disset mil·lèsim" : "disset mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 18 -> (numerador == 1 && centesimas == 0) ? "divuit mil·lèsim" : "divuit mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 19 -> (numerador == 1 && centesimas == 0) ? "dinou mil·lèsim" : "ginou mil " + leerDenominadorHasta1000(centesimas, numerador);
            case 20 -> (numerador == 1 && centesimas == 0) ? "vint mil·lèsim" : "vint mil " + leerDenominadorHasta1000(centesimas, numerador);
            default -> "";
        };
    }

    public static String leerDenominadorHasta100000(int denominador, int numerador) {
        if (denominador < 20000 && denominador != 1 ) return leerDenominadorHasta20000(denominador,numerador);
        if (denominador == 1) {
            return "";
        }
        int milesimas = denominador / 10000;
        int centesimas = denominador % 10000;
        return switch (milesimas) {
            case 2 -> (numerador == 1 && centesimas == 0) ? "vint mil·lèsim" : "vint-i-" + leerDenominadorHasta20000(denominador, numerador);
            case 3 -> (numerador == 1 && centesimas == 0) ? "trenta mil·lèsim" : "trenta-" + leerDenominadorHasta20000(denominador, numerador);
            case 4 -> (numerador == 1 && centesimas == 0) ? "quaranta mil·lèsim" : "quaranta-" + leerDenominadorHasta20000(denominador, numerador);
            case 5 -> (numerador == 1 && centesimas == 0) ? "cinquanta mil·lèsim" : "cinquanta-" + leerDenominadorHasta20000(denominador, numerador);
            case 6 -> (numerador == 1 && centesimas == 0) ? "seixanta mil·lèsim" : "seixanta-" + leerDenominadorHasta20000(denominador, numerador);
            case 7 -> (numerador == 1 && centesimas == 0) ? "setanta mil·lèsim" : "setanta-" + leerDenominadorHasta20000(denominador, numerador);
            case 8 -> (numerador == 1 && centesimas == 0) ? "vuitanta mil·lèsim" : "vuitanta-" + leerDenominadorHasta20000(denominador, numerador);
            case 9 -> (numerador == 1 && centesimas == 0) ? "noranta mil·lèsim" : "noranta-" + leerDenominadorHasta20000(denominador, numerador);
            case 10 -> (numerador == 1 && centesimas == 0) ? "cent mil·lèsim" : "cent mil " + leerDenominadorHasta20000(denominador, numerador);
            default -> "";
        };
    }



}