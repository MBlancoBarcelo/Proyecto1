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
            case 3 -> "trenta-i-" + leerNumeradorHasta19(unidades);
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
            case 10 -> "mil" + (decimales == 0 ? "" : " " + leerNumeradorHasta100(decimales));
            default -> "";
        };
    }

    public static String leerNumeradorHasta10000(int numerador) {
        if (numerador < 1000) return leerNumeradorHasta1000(numerador);
        int milesimas = numerador / 1000;
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
            default -> "";
        };
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
        if (esPotenciaDeDiez(denominador)){
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