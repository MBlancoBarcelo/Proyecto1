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

        String N = leerNumeradorHasta100(numerador);
        String D = leerDenominadorHasta100(denomirador, numerador);



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
        }

        return "LA FRACCION NO ES VALIDA";
    }

    //hace lo mismo que la anterior pero junta un prefijo dependiendo del numero de la variable decimales + un resultado
    //que coje de la funcion anterior con la variable unidades
    public  static String leerNumeradorHasta100(int numerador) {
        if (numerador < 20) return leerNumeradorHastaEl19(numerador);
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
        } else if (decimales == 10) {
            return "cent-" + leerNumeradorHastaEl19(unidades);
        }
        return "Numero incorrecto";
    }

    //Cuando le das un numero te lo tranforma en letras pero solo los 19 primeros te transforma los denominadores.
    public static String leerDenominadorHasta19(int denominador, int numerador) {
        if (denominador == 1) {
            return "unè";
        } else if (denominador == 2) {
            if (numerador == 1) {
                return "mig";
            }
            return "dosens";
        } else if (denominador == 3) {
            if (numerador == 1) {
                return "terç";
            }
            return "tresens";
        } else if (denominador == 4) {
            if (numerador == 1) {
                return "quart";
            }
            return "quarts";
        } else if (denominador == 5) {
            if (numerador == 1) {
                return "cinquè";
            }
           return "cinquens";
        } else if (denominador == 6) {
            if (numerador == 1) {
                return "sisè";
            }
            return "sisens";
        } else if (denominador == 7) {
            if (numerador == 1) {
                return "setè";
            }
            return "setens";
        } else if (denominador == 8) {
            if (numerador == 1) {
                return "vuitè";
            }
            return "vuitens";
        } else if (denominador == 9) {
            if (numerador == 1) {
                return "novè";
            }
            return "novens";
        } else if (denominador == 10) {
            if (numerador == 1) {
                return "dècim";
            }
            return "dècims";
        } else if (denominador == 11) {
            if (numerador == 1) {
                return "onzè";
            }
            return "onzens";
        } else if (denominador == 12) {
            if (numerador == 1) {
                return "dotzè";
            }
            return "dotzens";
        } else if (denominador == 13) {
            if (numerador == 1) {
                return "tretzè";
            }
            return "tretzens";
        } else if (denominador == 14) {
            if (numerador == 1) {
                return "catorzè";
            }
            return "catorzens";
        } else if (denominador == 15) {
            if (numerador == 1) {
                return "quienzè";
            }
            return "quinzens";
        } else if (denominador == 16) {
            if (numerador == 1) {
                return "setzè";
            }
            return "setzens";
        } else if (denominador == 17) {
            if (numerador == 1) {
                return "dissetè";
            }
            return "dissetens";
        } else if (denominador == 18) {
            if (numerador == 1) {
                return "divuitè";
            }
            return "divuitens";
        } else if (denominador == 19) {
            if (numerador == 1) {
                return "dinovè";
            }
            return "dinovens";
        }
        return "";
    }
    //hace lo mismo que la anterior pero junta un prefijo dependiendo del numero de la variable decimales + un resultado
    //que coje de la funcion anterior con la variable unidades
    public static String leerDenominadorHasta100 (int denominador,int numerador) {
        if (denominador < 20) return leerDenominadorHasta19(denominador,numerador);
        int decimales = denominador / 10;
        int unidades = denominador % 10;
        if (decimales == 2) {
            if (unidades == 0 && numerador == 1 ) {
                return "vintè";
            } else if (unidades == 0) {
                return "vintens";
            }
            return "vint-i-" + leerDenominadorHasta19(unidades, numerador);
        } else if (decimales == 3) {
            if (unidades == 0){
                return "trentè";
            }
            return "trenta-" + leerDenominadorHasta19(unidades,numerador) ;
        } else if (decimales == 4) {
            if (unidades == 0){
                return "quarentè";
            }
            return "quaranté-" + leerDenominadorHasta19(unidades,numerador);
        } else if (decimales == 5) {
            if (unidades == 0) {
                return "cinquantè";
            }
            return "cinquanta-" + leerDenominadorHasta19(unidades,numerador);
        } else if (decimales == 6) {
            if (unidades == 0) {
                return "seixantè";
            }
            return "seixanta-" + leerDenominadorHasta19(unidades,numerador);
        } else if (decimales == 7) {
            if (unidades == 0) {
                return "setantè";
            }
            return "setanta-" + leerDenominadorHasta19(unidades,numerador);
        } else if (decimales == 8) {
            if (unidades == 0) {
                return "vuitantè";
            }
            return "vuitanta-" + leerDenominadorHasta19(unidades,numerador);
        } else if (decimales ==  9) {
            if (unidades == 0) {
                return "norantè";
            }
            return "noranta-" + leerDenominadorHasta19(unidades,numerador);
        } else if (decimales == 10) {
            if (unidades == 0) {
                return "cent";
            }
            return "centesim-" + leerDenominadorHasta19(unidades,numerador);
        }
        return "Numero incorrecto";
    }
}

