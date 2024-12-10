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

        if (denominador == 0 ) {
            return "Indefinida";
        }

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
        String D = leerDenominadorHasta9999999(denominador);
        D = pluralOSingular(D, numerador, denominador);


        //Juntar numerador y denominador y pasar a mayuscula
        String resultado = N + " " + D;
        resultado = pasarAMayusculaPrimeraPalabra(resultado);
        return resultado.trim();
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
            return numeroenteropalabra.trim();
        } else {
            numerador = resto;
            String NE = leerNumeradorHasta9999999(numeroentero);
            String N = leerNumeradorHasta9999999(numerador);
            String D = leerDenominadorHasta9999999(denominador);
            D = pluralOSingular(D, numerador, denominador);
            String resultado =NE + ", " + N + " " + D;
            resultado = pasarAMayusculaPrimeraPalabra(resultado);
            return resultado.trim();
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
        return resultado.trim();
    }

    //Coge la primera letra del de una combinacion y la devuelve para que este en mayuscula la primera letra
    public static String pasarAMayusculaPrimeraPalabra(String resultado) {
        return resultado.substring(0, 1).toUpperCase() + resultado.substring(1);
    }

    //Como el pluralOSingular dara problemas a largo plazo con los unidades decimals etc he decidido cortar por lo sano
    //y creado este aparte para los casos especiales y unicos
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

    //Aqui lo redigira en el caso normal dado a que si es potencia de diez hara una cosa y si no el numerador es 1 o no
    //es uno sera singular prural
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


    //Este es de lo mas importante dado a que este diferencia entre las potencias de diez esto hace que sea muy importante
    //para el funcionamiento del codigo
    public static boolean esPotenciaDeDiez(int denominador) {
        int contador = 0;
        int numeroDenominador = denominador;
        if (denominador < 20) {
            return comprovacionDe10(numeroDenominador);
        }
        while ((denominador / 10 != 0))  {
            denominador /= 10;
            contador++;
        }
        return switch (contador) {
         case 1 -> comprovacionDe10(numeroDenominador);
         case 2 -> comprovacionDe100(numeroDenominador%100);
         case 3 -> comprovacionDe1000(numeroDenominador%1000);
         case 4 -> comprovacionDe10000(numeroDenominador%10000);
         case 5 -> comprovacionDe100000(numeroDenominador%100000);
         case 6 -> comprovacionDe1000000(numeroDenominador%1000000);
         default -> false;
        };
    }

    //Aqui viene una serie de codigo que cuando es separado dependiendo del numero de vueltas que de
    //sera un numero especifico
    public static boolean comprovacionDe10(int denominador) {
        if (denominador == 0) {
            return true;
        }
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return denominador == 1;
    }
    public static boolean comprovacionDe100(int denominador) {
        if (denominador == 0) {
            return true;
        }
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return denominador == 1;
    }

    public static boolean comprovacionDe1000(int denominador) {
        if (denominador == 1) {
            return false;
        }
        if (denominador == 0) {
            return true;
        }
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return true;
    }

    public static boolean comprovacionDe10000(int denominador) {
        if (denominador == 0) {
            return true;
        }
        if ((denominador >= 1) && (denominador <= 9)) {
            return false;
        }
        if ((denominador >= 1000))  {
            return false;
        }
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return true;
    }

    public static boolean comprovacionDe100000(int denominador){
        if (denominador == 0) {
            return true;
        }
        if ((denominador >= 1) && (denominador <= 9)) {
            return false;
        }
        if ((denominador >= 10000))  {
            return false;
        }
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return true;
    }

    public static boolean comprovacionDe1000000(int denominador){
        if (denominador == 0) {
            return true;
        }
        if ((denominador >= 1) && (denominador <= 9)) {
            return false;
        }
        if ((denominador >= 10000))  {
            return false;
        }
        while (denominador % 10 == 0) {
            denominador /= 10;
        }
        return true;
    }

    //cuando acabe la funcion PotenciaDeDiez y si es true llegara aqui y si el numerador es 1 devolvera una cosa y si no
    //otra
    public static String manejarPotenciaDeDiez(String denominador, int numerador) {
        if (numerador == 1) {
            return denominador; // No añadimos nada para el singular si es múltiple de 10
        } else {
            return denominador + "s"; // Añadimos 's' para el plural si es múltiple de 10
        }
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

    //Cuando le das un numero te lo tranforma en letras del 21 al 99
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

    //Hace el numerador desde al 100 al 999
    // a partir de aqui los codigos hacen cosas muy parecidas pero añadiendo numeros poco a poco
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

    //Hace el numerador desde al 1000 al 20000
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
            default -> leerNumeradorHasta1000(centesima);
        };
    }

    //Hace el numerador desde al 20001 al 999999
    public static String leerNumeradorHasta100000(int numerador) {
        if (numerador < 20000) return leerNumeradorHasta20000(numerador);
        int milesimas = numerador / 10000;
        milesimas = milesimas % 10;
        int centesima = numerador % 10000;
        return switch (milesimas) {
            case 2 -> "vint" + (centesima == 0 ? "mil" : "-i-" + leerNumeradorHasta20000(centesima));
            case 3 -> "trenta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            case 4 -> "quaranta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            case 5 -> "cinquanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            case 6 -> "seixanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            case 7 -> "setanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            case 8 -> "vuitanta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            case 9 -> "noranta" + (centesima == 0 ? "mil" : "-" + leerNumeradorHasta20000(centesima));
            default ->  "";
        };
    }

    //Hace el numerador desde al 100000 al 1000000
    public static String leerNumeradorHasta1000000(int numerador) {
        if (numerador < 100000) return leerNumeradorHasta100000(numerador);
        int milesimas = numerador / 100000;
        int centesima = numerador % 100000;
        return switch (milesimas) {
            case 1 -> "cent " + (centesima == 0 ? "mil" : "mil " + leerNumeradorHasta100000(centesima));
            case 2 -> "dos-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(centesima));
            case 3 -> "tres-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(centesima));
            case 4 -> "quatre-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(centesima));
            case 5 -> "cinc-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(centesima));
            case 6 -> "sis-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(centesima));
            case 7 -> "set-cents " + (centesima == 0 ? "mil" :  leerNumeradorHasta100000(centesima));
            case 8 -> "vuit-cents " + (centesima == 0 ? "mil" : leerNumeradorHasta100000(centesima));
            case 9 -> "nou-cents " + (centesima == 0 ? "mil" :  leerNumeradorHasta100000(centesima));
            case 10 -> "un miliò" + (centesima == 0 ? "" : leerNumeradorHasta100000(centesima));
            default -> "";
        };
    }

    //Hace el numerador desde al 10000000 al 9999999
    public static String leerNumeradorHasta9999999(int numerador) {
        if (numerador < 1000000) return leerNumeradorHasta1000000(numerador);
        int milesimas = numerador / 1000000;
        int centesima = numerador % 1000000;
        return switch (milesimas) {
            case 1 -> "un milió" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 2 -> "dos milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 3 -> "tres milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 4 -> "quatre milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 5 -> "cinc milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 6 -> "sis milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 7 -> "set miliò" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 8 -> "vuit milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            case 9 -> "nou milions" + (centesima == 0 ? "" : " " + leerNumeradorHasta1000000(centesima));
            default -> "";
        };
    }

    //comprueba que el denominador es 2,3 o 4
    public static String leerDenominadorSiEsEspecial(int denominador) {
        return switch (denominador) {
            case 2 -> "mig";
            case 3 -> "terç";
            case 4 -> "quart";
            default -> "";
        };
    }

    //comprueba que el denominador es del 1 al 20 pero no es especial ejemplo un 34 seria trenta-quatre
    public static String leerDenominadorHasta19(int denominador) {
        if (denominador > 20 ) {
            denominador = denominador % 100;
        }
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

    //Aqui el denominador seria desde 21 hasta el 100 Ex:30/40/78 a partir de aqui las funciones seran muy parecidas
    //pero iran aumentando los numeros
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
            default -> leerDenominadorHasta19(denominador);
        };
    }

    //Comprueba si se debe poner un esim o nada dependiendo del de los restos de que tiene
    public static String comprobacionDeCentOCentesim (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0)){
            return "èsim";
        } else {
            return "";
        }
    }

    //Hace el denominador del 101 hasta el 1000 Ex:2481/9875/3758
    public static String leerDenominadorHasta1000(int denominador) {
        if (denominador <= 100) return leerDenominadorHasta100(denominador);
        int centesima = denominador / 100;
        int decimales = denominador % 100;
        return switch (centesima) {
            case 1 -> "cent"  + comprobacionDeCentOCentesim(denominador) + " " + leerDenominadorHasta100(decimales);
            case 2 -> "dos-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 3 -> "tres-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 4 -> "quatre-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 5 -> "cinc-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 6 -> "sis-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 7 -> "set-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 8 -> "vuit-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            case 9 -> "nou-cent" + comprobacionDeCentOCentesim(denominador) + "s " + leerDenominadorHasta100(decimales);
            default -> leerDenominadorHasta100(denominador);
        };
    }

    //Aqui iria un codigo que con un if y unas comprobaciones a los restos de denominador entre 10 100 1000 pueda diferenciar entre poner mil o millesim
    public static String comprobacionDeMilOMilmilesim (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0)){
            return "·lèsim";
        } else {
            return " ";
        }
    }
    //Es una comprobacion especial que solo hace para el mil para que añada un un o que no devuelva nada si el resto
    //no es el que queremos
    public static String comprobacionDelUn (int denominador) {
        if (denominador / 10000 != 0) {
            return "un ";
        } else {
            return "";
        }
    }

    //Este diferenciaria entre 1001 al 20000 porque a esta escala son muy parecidas del 1 al 20
    public static String leerDenominadorHasta20000(int denominador) {
        if (denominador < 1000 && denominador != 1) return leerDenominadorHasta1000(denominador);
        int milesimas = denominador / 1000;
        if (milesimas > 20) {
            int separador = denominador % 10000;
            milesimas = separador / 1000;
        }
        int centesimas = denominador % 1000;
        return switch (milesimas) {
            case 1 -> comprobacionDelUn(denominador) + "mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 2 -> "dos mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 3 -> "tres mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 4 -> "quatre mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 5 -> "cinc mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 6 -> "sis mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 7 -> "set mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 8 -> "vuit mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 9 -> "nou mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 10 -> "deu mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 11 -> "onze mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 12 -> "dotze mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 13 -> "trezte mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 14 -> "catorze mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 15 -> "quinze mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 16 -> "setze mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 17 -> "disset mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 18 -> "divuit mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 19 -> "dinou mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            case 20 -> "vint mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta1000(centesimas);
            default -> leerDenominadorHasta1000(denominador);
        };
    }

    //Es una comprobacion especial que es unica para el 200000 para saber si añade un -i- o no añade nada ejemplo
    //vint-i-une mil o vint mil
    public static String comprovacionDeMilOMilmilesimEx20 (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0) && (denominador / 1000 != 20)){
            return "-i-";
        } else {
            return "";
        }
    }

    //Este es para el resto de casos del 100000 Ejemplo:trenta mil, trenta-cinc mil
    public static String comprovacionDeMilOMilmilesim100000 (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0) && (denominador % 10000 == 0 )){
            return " ";
        } else {
            return "-";
        }
    }

    //Esto es para leer el caso de 20001 hasta el 100000
    public static String leerDenominadorHasta100000(int denominador) {
        if (denominador <= 20000 && denominador != 1 ) return leerDenominadorHasta20000(denominador);
        int milesimas = denominador / 10000;
        if (milesimas > 10) {
            milesimas = milesimas % 10;
        }
        return switch (milesimas) {
            case 2 -> "vint" + comprovacionDeMilOMilmilesimEx20(denominador) + leerDenominadorHasta20000(denominador);
            case 3 -> "trenta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 4 -> "quaranta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 5 -> "cinquanta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 6 -> "seixanta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 7 -> "setanta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 8 -> "vuitanta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 9 -> "noranta" + comprovacionDeMilOMilmilesim100000(denominador) + leerDenominadorHasta20000(denominador);
            case 10 -> "cent mil" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta20000(denominador);
            default -> leerDenominadorHasta20000(denominador);
        };
    }

        //Este denominador lee del 100000 hasta el 999999
        public static String leerDenominadorHasta1000000(int denominador) {
        if (denominador < 100000) return leerDenominadorHasta100000(denominador);
        int milesimas = denominador / 100000;
        if (milesimas > 10) {
            milesimas = milesimas % 10;
        }
        return switch (milesimas) {
            case 2 -> "dos-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 3 -> "tres-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 4 -> "quatre-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 5 -> "cinc-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 6 -> "sis-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 7 -> "set-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 8 -> "vuit-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            case 9 -> "nou-cents" + comprobacionDeMilOMilmilesim(denominador) + leerDenominadorHasta100000(denominador);
            default -> leerDenominadorHasta100000(denominador);
        };
    }
    //Comprueba muy parecido a los anteriores pero hace el de millones
    public static String comprobacionDeMilOMilmilesim1000000 (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0) && (denominador % 100000 == 0 )){
            return "onèsim";
        } else {
            return "ons ";
        }
    }
    //Este es especial dado a que el primero es especial ya que es diferentes a los demas ya que no lleva ons asi
    //que es especial y solo funciona para el
    public static String comprobacionDeMilOMilmilesimEspecial1000000 (int denominador) {
        if ((denominador % 10 == 0) && (denominador % 100 == 0) && (denominador % 1000 == 0) && (denominador % 100000 == 0 )) {
            return "onèsim";
        } else {
            return "ó ";
        }
    }

    //Este Nos lleva al limite de nuestro programa del denominador hasta el 9999999
    public static String leerDenominadorHasta9999999(int denominador) {
        if (denominador < 1000000 && denominador != 1 ) return leerDenominadorHasta1000000(denominador);
        int milesimas = denominador / 1000000;
        int resto = denominador % 1000000;
        return switch (milesimas) {
            case 1 -> "mili" + comprobacionDeMilOMilmilesimEspecial1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 2 -> "dos mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 3 -> "tres mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 4 -> "quatre mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 5 -> "cinc mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 6 -> "sis mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 7 -> "set mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 8 -> "vuit mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            case 9 -> "nou mili" + comprobacionDeMilOMilmilesim1000000(denominador) + leerDenominadorHasta1000000(resto);
            default -> "";
        };
    }

}