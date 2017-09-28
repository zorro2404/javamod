package CommunictionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import Channels.ChannelInstance;
import jsonbuilder.JsonUtil;

public class ConfigMaster {
	private ArrayList<ConfigMaster.Channel> outstations;



	public static ConfigMaster fromFile(String pathToFile) {
		File file = new File(pathToFile);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(file.toPath());
			String content = new String(bytes, "UTF-8");
			System.out.println(content);
			Class epr=ConfigMaster.class; 
			return (ConfigMaster) JsonUtil.ParsetoJson(content,ConfigMaster.class); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ConfigMaster.Channel> GetOutstations(){
		return this.outstations;
	}


	public void SetOutstations(ArrayList<ConfigMaster.Channel> Out) {
		this.outstations=Out;
	}




	public class Channel {

		Protocol type;
		String ip;
		String device;
		int port;
		ArrayList<Master> ids;

		public Protocol getType() {
			return type;
		}

		public void setType(Protocol type) {
			this.type = type;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getDevice() {
			return device;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public ArrayList<Master> getIds() {
			return ids;
		}

		public void setIds(ArrayList<Master> ids) {
			this.ids = ids;
		}


	}



	public class Master {
		int idmaster;
		int idoutstation;
		String Adressip;
		boolean useconfirmes;
		int numretry;
		int timeout;



		public int getIdmaster() {
			return idmaster;
		}
		public void setIdmaster(int idmaster) {
			this.idmaster = idmaster;
		}
		public int getIdoutstation() {
			return idoutstation;
		}
		public void setIdoutstation(int idoutstation) {
			this.idoutstation = idoutstation;
		}
		public boolean isUseconfirmes() {
			return useconfirmes;
		}
		public void setUseconfirmes(boolean useconfirmes) {
			this.useconfirmes = useconfirmes;
		}
		public int getNumretry() {
			return numretry;
		}
		public void setNumretry(int numretry) {
			this.numretry = numretry;
		}
		public int getTimeout() {
			return timeout;
		}
		public void setTimeout(int timeout) {
			this.timeout = timeout;
		}

		public String getIpadress() {
			return this.Adressip;
		}

		public void setIpadress(String ip) {
			this.Adressip=ip;
		}
	}

}





