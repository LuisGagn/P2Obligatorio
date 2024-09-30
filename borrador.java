 public static void imprimirConsola(char[][] tablero, int[] posicion){
        
        // 4 cols de *
        // 9 cols X , O 
        // 6 cols |
        
        // * En valores 0, 6, 12, 18 -- Filas
        // * En valores 0, 6, 12, 18 -- Cols
        int actualRow = 0;
        int actualCol = 0;
        
        Integer[] ast = {0, 6, 12, 18};
        int countF = 0;
        
        
        String separadorA = "* * * * * * ";
        String separadorB = "- + - + - ";
        
        
        // posicion[0] --> 0, 3 , 6 --> 0, 1 y 2 | 3, 2 y 3 | 6, 3 y 4. 
        // posicion[1] --> 0, 3 , 6 --> 0, 1 y 2 | 3, 2 y 3 | 6, 3 y 4. 
        
       String vert = "";
       int horz = 0;
       vert = switch (posicion[0]) {
            case 0 -> "12";
            case 3 -> "23";
            case 6 -> "34";
            default -> "";
        };   
        
       horz = switch (posicion[1]) {
            case 0 -> 1;
            case 3 -> 2;
            case 6 -> 3;
            default -> 0;
        };   
        
        
        
        int contadorHorizontal =1;
        int contadorHorizontal2 =0;
        int RADIX = 10;
        for(int i =0; i < tablero.length; i++){
            
            // Separador de ****** 3 secciones
            if(i%3==0){
                for(int k = 1; k <=3; k++){
                if(vert.contains(Integer.toString(contadorHorizontal)) && horz == k){
                System.out.print(Colors.YELLOW_BACKGROUND+separadorA+"* "+Colors.RESET);

                }else{
                System.out.print(Colors.GREEN_BACKGROUND+separadorA+Colors.RESET);
                        }
                
                }
                contadorHorizontal++;
                contadorHorizontal2++;
                System.out.println();
            }
            
            
            
            
            // FOR que imprima lo mismo 3 veces pero con color segun A B o C
            if(countF%3 != 0){
                
              
              for(int p =1; p <=3; p ++){
                if(Character.digit(vert.charAt(0), RADIX) == contadorHorizontal2 && horz == p ){
               System.out.print(Colors.YELLOW_BACKGROUND+"* "+Colors.RESET+separadorB+Colors.YELLOW_BACKGROUND);
                }else{ 
                    if(Character.digit(vert.charAt(0), RADIX) == contadorHorizontal2 && horz == p-1 ){
               System.out.print("* "+Colors.RESET+separadorB);
                }else{
                       System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET+separadorB+Colors.GREEN_BACKGROUND); 
                    }
              }
              }
              if(Character.digit(vert.charAt(0), RADIX)==contadorHorizontal2 && horz == 3){
              System.out.print(Colors.YELLOW_BACKGROUND+"* "+Colors.RESET);
              }else {
                  System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
              }
                System.out.println();
            }

            
            
            
            
            
            
            
            
            
            int count = 1;
            
            
            for(int j = 0; j < tablero.length; j++){
                if(j%3 == 0){ 
                    System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
                    System.out.print(tablero[i][j]+" | ");
                }else{
                    if(count%3!=0){
                System.out.print(tablero[i][j]+" | ");
                }
                    else {
                        System.out.print(tablero[i][j]+" ");
                    }
                }
                
                if(j == tablero.length-1){
                    System.out.print(Colors.GREEN_BACKGROUND+"* "+Colors.RESET);
                }
                
                count++;
            }
            
            
                        if(i==tablero.length-1){
                            System.out.println();
                System.out.print(Colors.GREEN_BACKGROUND+"* * * * * * * * * * * * * * * * * * *"+Colors.RESET);
                
            }
            
            System.out.println();
            countF++;
        }
        
        
       System.out.println("HORIZONTAL = " + vert+ " "+" "+ contadorHorizontal);
        
    } 
    
}
