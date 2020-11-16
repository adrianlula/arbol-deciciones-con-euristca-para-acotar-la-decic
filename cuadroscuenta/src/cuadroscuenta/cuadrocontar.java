package cuadroscuenta;


import java.util.ArrayList;
import java.util.Arrays;

public class cuadrocontar {

	public static void main(String[] args) {
		int[][] inicial={{1,2,3},{4,5,6},{7,0,8}};
        int[][] solucion={{1,2,3},{4,5,6},{7,8,0}};
       nodo inicio=new nodo(inicial);
      nodo sol= buscarsolucion(inicio,solucion);
      while(sol.padre!=null){
      	  imprimirestado(sol.getestado());
    	  System.out.println("::::::::::::");
    	  sol=sol.padre;
      }
      imprimirestado(inicio.getestado());
	}
	
	
	public static nodo  buscarsolucion(nodo inicio,int[][] solucion){
		ArrayList<nodo> expandidos=new ArrayList<nodo>();
		expandidos.add(inicio);
		int movimientos=0;
		ArrayList<nodo> creados=new ArrayList<nodo>();
		
		while(expandidos.size()!=0){
			
			nodo revisar=expandidos.remove(0);
			imprimirestado(revisar.getestado());
			int [] pocicioncero=ubicarnumero(revisar.getestado(),0);
			System.out.println(pocicioncero[0]+","+pocicioncero[1]);
			System.out.println("movimientos realizados: "+(movimientos++));
			if(Arrays.deepEquals(revisar.getestado(),solucion)){
				System.out.println("resuelto encontrado");
				return revisar;
			}
			ArrayList<nodo> hijos=new ArrayList<nodo>();
			
			
			if(pocicioncero[0]!=0){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int arriba=hijo.getestado()[pocicioncero[0]-1][pocicioncero[1]];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=arriba;
				hijo.getestado()[pocicioncero[0]-1][pocicioncero[1]]=0;

				creados.add(hijo);
				hijos.add(hijo);
			}
			if(pocicioncero[0]!=2){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int abajo=hijo.getestado()[pocicioncero[0]+1][pocicioncero[1]];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=abajo;
				hijo.getestado()[pocicioncero[0]+1][pocicioncero[1]]=0;
				creados.add(hijo);
				hijos.add(hijo);
		
			}
			
			if(pocicioncero[1]!=0){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int izquierda=hijo.getestado()[pocicioncero[0]][pocicioncero[1]-1];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=izquierda;
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]-1]=0;
				creados.add(hijo);
				hijos.add(hijo);
		
			}
			if(pocicioncero[1]!=2){
				nodo hijo=new nodo (clonar(revisar.getestado()));
				int derecha=hijo.getestado()[pocicioncero[0]][pocicioncero[1]+1];
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]]=derecha;
				hijo.getestado()[pocicioncero[0]][pocicioncero[1]+1]=0;
				creados.add(hijo);
				
				hijos.add(hijo);
			}
			
				while(creados.size()!=0){
					nodo calcular=creados.remove(0);
			int wow=contar(calcular.getestado());
			nodo calcular2=creados.remove(0);
		      int ya=contar(calcular2.getestado());
		      if(wow!=0&&wow<ya){
		    expandidos.add(calcular);	
		    if(creados.size()!=0){
		    	creados.add(calcular);
		    } 	
		      }else if(ya!=0&&ya<wow){
		    expandidos.add(calcular2);	
		    if(creados.size()!=0){
		    	creados.add(calcular2);
		    }
		      }
		      
			} 
				
				
			
				
			
			
					
					
			
			
			
			revisar.sethijos(hijos);
			
		}
		return null;
	}
	private static int contar(int[][] estadoanalizar) {
		int num=0;
		ArrayList<int[]> numeros=new ArrayList<int[]>();
		ArrayList<int[]> numerossolucion=new ArrayList<int[]>();
		for(int i=0;i<9;i++){
			int [] numero=new int[2];
			numero=ubicarnumero(estadoanalizar,i);
		  numeros.add(numero);
		}	
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					int [] numerosol=new int[2];
					numerosol[0]=j-1;
					numerosol[1]=k-1;
					numerossolucion.add(numerosol);
				}
				}
			numerossolucion.remove(0);
			int [] numerosolcero=new int[2];
			numerosolcero[0]=2;
			numerosolcero[0]=2;
			numerossolucion.add(numerosolcero);
			for(int i=0;i<9;i++){
				int [] numeroanalisar=new int[2];
				int [] numerosdesoluciones=new int[2];
				numeroanalisar=numeros.remove(0);
				numerosdesoluciones=numerossolucion.remove(0);
				if((numeroanalisar[0]-numerosdesoluciones[0])<0){
				num=num-(numeroanalisar[0]-numerosdesoluciones[0]);
			}else if((numeroanalisar[0]-numerosdesoluciones[0])<0){
				num=num+(numeroanalisar[0]-numerosdesoluciones[0]);
			}
			}
		return num;
	}



	private static int[][] clonar(int[][] estado) {
		int[][] clon=new int[estado.length][estado.length];
		for(int i=0;i<estado.length;i++){
			for(int j=0;j<estado.length;j++){
			clon[i][j]=estado[i][j];
			}
		}
		return clon;
	}


	private static int[] ubicarnumero(int[][] estado,int n) {
	int[] posicion=new int[2];
		for(int i=0;i<estado.length;i++){
			for(int j=0;j<estado.length;j++){
				if(estado[i][j]==0){
					posicion[0]=i;
					posicion[1]=j;
					}
			}
		}
		return posicion;
		}
	


	public static void imprimirestado(int[][] estado){
		for(int i=0;i<estado.length;i++){
			for(int j=0;j<estado.length;j++){
				System.out.print("["+estado[i][j]+"]");
			}
			System.out.println("");
		}
	}

}
