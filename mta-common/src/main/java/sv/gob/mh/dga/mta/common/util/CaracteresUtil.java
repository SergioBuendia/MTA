package sv.gob.mh.dga.mta.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class CaracteresUtil {
	public static Integer contarCaracteres(String cadena, String caracter) {
		Integer posicion, contador = 0;
		posicion = cadena.indexOf(caracter);
        while (posicion != -1) { 
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
	}
	
	public static String limpiarComentarios(String cadena, String caracterComentario){
		
		StringBuilder strConsultaLimpia = new StringBuilder();
		
 		
		BufferedReader bufReader = new BufferedReader(new StringReader(cadena));
		String line=null;

		try {
			while( (line = bufReader.readLine()) != null )
			{
				int comentario= line.indexOf(caracterComentario);
				if(comentario==-1){
					if(strConsultaLimpia.length()>0)
						strConsultaLimpia.append("\n");
					strConsultaLimpia.append(line);
				}else if (comentario>0){
					String residuo = line.substring(0,comentario);
					if(residuo.trim().length()>0){
						if(strConsultaLimpia.length()>0)
							strConsultaLimpia.append("\n");
						strConsultaLimpia.append(residuo);
					}
					
				}else{
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strConsultaLimpia.toString();
	}
	
	public static String removerSaltosLinea( String cadena) { 
	    return cadena.replaceAll("[\\n\\t]", "");

	}

	public static String reemplazaSaltosLinea(String cadena) {
		if (cadena != null)
			return cadena.replaceAll("\r", "<br/>").replaceAll("\n", "<br/>");
		else
			return "";
	} 
}
