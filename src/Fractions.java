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

        if (numerador > denominador) {
            return siElNumeradorEsMasGradeQueElDenominador(numerador,denominador);
        }

        //Comprueba que los denominador no sea uno especial que en este caso serian del 1 al 4
        if (denominador <= 4) {
            return comprovacionDeCasosEspeciales(numerador,denominador);
        }

        String N = leerNumeradorHasta9999999(numerador);
        String D = leerDenominadorHasta9999999(denominador, numerador);
        D = pluralOSingular(D, numerador, denominador);


        //Juntar numerador y denominador y pasar a mayuscula
        String resultado = N + " " + D;
        resultado = pasarAMayusculaPrimeraPalabra(resultado);
        return resultado;
    }
    //Este codigo dividira los numeros si el numerador es mas grande que el denominador luego si tiene resto o no
    //si tiene resto le pondra el valor de resto a numerador y le pondra el numero entero a la frase si no
    //simplemente dira el numero sin nada mas
    public static String siElNumeradorEsMasGradeQueElDenominador (int numerador,int denominador) {
        int numeroentero = numerador / denominador;
        int resto = numerador % denominador;
        if (resto == 0) {
            String numeroenteropalabra = leerNumeradorHasta9999999(numeroentero);
            numeroenteropalabra = pasarAMayusculaPrimeraPalabra(numeroenteropalabra);
            return numeroenteropalabra;
        } else {
            numerador = resto;
            String NE = leerNumeradorHasta9999999(numeroentero);
            String N = leerNumeradorHasta9999999(numerador);
            String D = leerDenominadorHasta9999999(denominador, numerador);
            D = pluralOSingular(D, numerador, denominador);
            String resultado =NE + ", " + N + " " + D;
            resultado = pasarAMayusculaPrimeraPalabra(resultado);
            return resultado;
        }
    }

    //Comprueba si el denominador va del 1 al 4 para saber si es nesecario un caso como un mig terç o quart luego le da
    //su prural o singular para montar la frase.
    public static String comprovacionDeCasosEspeciales (int numerador,int denominador) {
        String N = leerNumeradorHasta9999999(numerador);
        String D = leerDenominadorSiEsEspecial(denominador);
        D = pruralOSingularEspecial(D, numerador);
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
        milesimas = milesimas % 10;
        int centesima = numerador % 10000;
        return switch (milesimas) {
            case 2 -> "vint" + (centesima == 0 ? "mil" : "-i-" + leerNumeradorHasta20000(numerador));
            case 3 -> "trenta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            case 4 -> "quaranta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            case 5 -> "cinquanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            case 6 -> "seixanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            case 7 -> "setanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            case 8 -> "vuitanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            case 9 -> "noranta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(numerador));
            default ->  "";
        };
    }

    public static String leerNumeradorHasta1000000(int numerador) {
        if (numerador < 100000) return leerNumeradorHasta100000(numerador);
        int milesimas = numerador / 100000;
        int centesima = numerador % 100000;
        return switch (milesimas) {
            case 1 -> "cent " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 2 -> "dos-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 3 -> "tres-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 4 -> "quatre-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 5 -> "cinc-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(numerador));
            case 6 -> "sis-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 7 -> "set-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 8 -> "vuit-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 9 -> "nou-cents " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(numerador));
            case 10 -> "un miliò" + (centesima == 0 ? "" : " " + leerNumeradorHasta100000(numerador));
            default -> "";
        };
    }

    public static String leerNumeradorHasta9999999(int numerador) {
        if (numerador < 1000000) return leerNumeradorHasta1000000(numerador);
        int milesimas = numerador / 1000000;
        int centesima = numerador % 1000000;
        return switch (milesimas) {
            case 1 -> "un miliò " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 2 -> "dos milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 3 -> "tres milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 4 -> "quatre milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 5 -> "cinc milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 6 -> "sis milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 7 -> "set miliò " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 8 -> "vuit milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
            case 9 -> "nou milions " + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(numerador));
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

    //Aqui iria un codigo que con un if y unas comprovaciones a los restos de denominador entre 10 100  pueda diferenciar entre poner mil o millesim

    public static String comprovacionDeCentOCentesim (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0)){
            return "èsim";
        } else {
            return "";
        }
    }


    public static String leerDenominadorHasta1000(int denominador) {
        if (denominador <= 100) return leerDenominadorHasta100(denominador);
        int centesima = denominador / 100;
        int decimales = denominador % 100;
        return switch (centesima) {
            case 1 -> "cent"  + comprovacionDeCentOCentesim(denominador) + " " + leerDenominadorHasta100(decimales);
            case 2 -> "dos-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 3 -> "tres-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 4 -> "quatre-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 5 -> "cinc-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 6 -> "sis-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 7 -> "set-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 8 -> "vuit-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 9 -> "nou-cent" + comprovacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            default -> "";
        };
    }

    //Aqui iria un codigo que con un if y unas comprovaciones a los restos de denominador entre 10 100 1000 pueda diferenciar entre poner mil o millesim
    public static String comprovacionDeMilOMilmilesim (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0)){
            return "·lèsim";
        } else {
            return "";
        }
    }

    public static String leerDenominadorHasta20000(int denominador) {
        if (denominador < 1000 && denominador != 1) return leerDenominadorHasta1000(denominador);
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
            case 1 -> "mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 2 -> "dos mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 3 -> "tres mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 4 -> "quatre mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 5 -> "cinc mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 6 -> "sis mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 7 -> "set mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 8 -> "vuit mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 9 -> "nou mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 10 -> "deu mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 11 -> "onze mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 12 -> "dotze mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 13 -> "trezte mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 14 -> "catorze mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 15 -> "quinze mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 16 -> "setze mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 17 -> "disset mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 18 -> "divuit mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 19 -> "dinou mil" + comprovacionDeMilOMilmilesim(denominador) +leerDenominadorHasta1000(centesimas);
            case 20 -> "vint mil" + comprovacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            default -> "";
        };
    }

    public static String leerDenominadorHasta100000(int denominador, int numerador) {
        if (denominador < 20000 && denominador != 1 ) return leerDenominadorHasta20000(denominador,numerador);
        if (denominador == 1) {
            return "";
        }
        int milesimas = denominador / 10000;
        if (milesimas > 10) {
            milesimas = milesimas % 10;
        }
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



    public static String leerDenominadorHasta1000000(int denominador, int numerador) {
        if (denominador < 100000 && denominador != 1 ) return leerDenominadorHasta100000(denominador,numerador);
        if (denominador == 1) {
            return "";
        }
        int milesimas = denominador / 100000;
        if (milesimas > 10) {
            milesimas = milesimas % 10;
        }
        int centesimas = denominador % 100000;
        return switch (milesimas) {
            case 2 -> (numerador == 1 && centesimas == 0) ? "dos-cents mil·lèsim" : "dos-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            case 3 -> (numerador == 1 && centesimas == 0) ? "tres-cents mil·lèsim" : "tres-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            case 4 -> (numerador == 1 && centesimas == 0) ? "quatre-cents mil·lèsim" : "quatre-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            case 5 -> (numerador == 1 && centesimas == 0) ? "cinc-cents mil·lèsim" : "cinc-cents " + leerDenominadorHasta100000(denominador, numerador);
            case 6 -> (numerador == 1 && centesimas == 0) ? "sis-cents mil·lèsim" : "sis-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            case 7 -> (numerador == 1 && centesimas == 0) ? "set-cents mil·lèsim" : "set-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            case 8 -> (numerador == 1 && centesimas == 0) ? "vuit-cents mil·lèsim" : "vuit-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            case 9 -> (numerador == 1 && centesimas == 0) ? "nou-cents mil·lèsim" : "nou-cents mil " + leerDenominadorHasta100000(denominador, numerador);
            default -> "";
        };
    }

    public static String leerDenominadorHasta9999999(int denominador, int numerador) {
        if (denominador < 1000000 && denominador != 1 ) return leerDenominadorHasta1000000(denominador,numerador);
        if (denominador == 1) {
            return "";
        }
        int milesimas = denominador / 1000000;
        int centesimas = denominador % 1000000;
        return switch (milesimas) {
            case 1 -> (numerador == 1 && centesimas == 0) ? "milionèsim" : "milionèsim" + leerDenominadorHasta1000000(denominador, numerador);
            case 2 -> (numerador == 1 && centesimas == 0) ? "dos milionèsim" : "dos milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 3 -> (numerador == 1 && centesimas == 0) ? "tres milionèsim" : "tres milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 4 -> (numerador == 1 && centesimas == 0) ? "quatre milionèsim" : "quatre milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 5 -> (numerador == 1 && centesimas == 0) ? "cinc milionèsim" : "cinc milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 6 -> (numerador == 1 && centesimas == 0) ? "sis milionèsim" : "sis milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 7 -> (numerador == 1 && centesimas == 0) ? "set milionèsim" : "set milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 8 -> (numerador == 1 && centesimas == 0) ? "vuit milionèsim" : "vuit milions " + leerDenominadorHasta1000000(denominador, numerador);
            case 9 -> (numerador == 1 && centesimas == 0) ? "nou milionèsim" : "nou milions " + leerDenominadorHasta1000000(denominador, numerador);
            default -> "";
        };
    }


}