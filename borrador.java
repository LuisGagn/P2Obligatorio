 public static void imprimirConsola(char[][] tablero, int[] seleccion, int[][] testigo){

        int filaImpresa = 0;
        
        int[] cuadrante = seleccionCuadrante(seleccion);
        
       // 0 | 6  | 12 | 18 
        String divisor = " - + - + - ";   
        
        for(int i =0; i < tablero.length; i ++){
            
            // Imprime separadores horizontales
            if(filaImpresa%6==0){
            switch (cuadrante[0]){
                case 1 -> {
                    if(filaImpresa == 0 || filaImpresa == 6){
                        imprimirSeparador(cuadrante[1]);
                    } else {
                        imprimirSeparador(0);
                    }
                }
                case 2 -> {
                    if(filaImpresa == 6 || filaImpresa == 12){
                        imprimirSeparador(cuadrante[1]);
                    }else {
                        imprimirSeparador(0);
                    }
                }
                case 3 -> {
                    if(filaImpresa == 12 || filaImpresa == 18){
                        imprimirSeparador(cuadrante[1]);
                    }else {
                        imprimirSeparador(0);
                    }
                }
                default -> {
                        imprimirSeparador(0);
                }
            }
            System.out.println();
            filaImpresa++;
            }
            
            int colImpresa =0;
            int elemImpreso = 0;
            for(int j = 0; j<tablero.length; j++){
                if(colImpresa%6==0){
                switch (cuadrante[1]){
                    case 1 -> {
                        if(filaImpresa>0 && filaImpresa<6){
                            imprimirAstk(cuadrante[0], colImpresa);
                        } else {
                            imprimirAstk(0,0);
                        }
                    }
                    case 2 -> {
                        if(filaImpresa>6 && filaImpresa<12){
                            imprimirAstk(cuadrante[0], colImpresa);
                        }else {
                            imprimirAstk(0,0);
                        }
                    }
                    case 3 -> {
                        if(filaImpresa>12 && filaImpresa<18){
                            imprimirAstk(cuadrante[0], colImpresa);
                        }else {
                            imprimirAstk(0,0);
                        }
                    }
                    default -> {
                        imprimirAstk(0,0);
                         
                    }
                }
                  colImpresa++;
                }
                
             
               
              // Cambiar por funciones de coloreado
              
              System.out.print(" "+tablero[i][j]);
              colImpresa++;
              elemImpreso++;
              
              if(elemImpreso != 3){
              System.out.print("|");
              colImpresa++;
              } else{
                  elemImpreso=0;
              }
              
             
              
            }
            filaImpresa++;
            System.out.println();
            if(filaImpresa%6 !=0 ){
            imprimirAstk(0,0);
            System.out.print(" -+ -+ -");
            imprimirAstk(0,0);
            System.out.print(" -+ -+ -");
            imprimirAstk(0,0);
            System.out.print(" -+ -+ -");
            imprimirAstk(0,0);
            System.out.println();
            filaImpresa++;
            }
         
        }

        
        
        
        
        
        
    }