package servidor;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException 
        {
		Servidor srv = new Servidor();
		while(true)
                {
                    if(srv.getFinish() == 1)
                    {
                        break;
                    }
                }
	}
}
