package application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api") // Prefixo criado no web.xml /api/
public class RestAPI extends Application{ // RestAPI é apenas o nome escolhido, pode ser outro qualquer.

}
