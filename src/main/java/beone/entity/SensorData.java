package beone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sensor_data")
public class SensorData {
	@Id
	private long time;
	@Column
	private float Power;
	@Column
	private float Temp;
	@Column
	private float ORP;
	@Column
	private float Depth;
	@Column
	private float PH;
	@Column
	private float OO;
	@Column
	private float CON;
	
	public float getPower() {
		return Power;
	}
	public void setPower(float power) {
		Power = power;
	}
	public float getTemp() {
		return Temp;
	}
	public void setTemp(float temp) {
		Temp = temp;
	}
	public float getDepth() {
		return Depth;
	}
	public void setDepth(float depth) {
		Depth = depth;
	}
	public float getPH() {
		return PH;
	}
	public void setPH(float pH) {
		PH = pH;
	}
	public float getOO() {
		return OO;
	}
	public void setOO(float oO) {
		OO = oO;
	}
	public float getCON() {
		return CON;
	}
	public void setCON(float cON) {
		CON = cON;
	}

	public float getORP() {
		return ORP;
	}
	public void setORP(float oRP) {
		ORP = oRP;
	}
//	private Data data;
	
	public SensorData() {
	}
	public class Data{
		private float Power;
		private float Temp;
		private float Depth;
		private float PH;
		private float OO;
		private float CON;
		private float ORP;
	
		public Data() {
		}
		public Data(float power, float temp, float depth, float pH, float oO, float cON, float oRP) {
			super();
			Power = power;
			Temp = temp;
			Depth = depth;
			PH = pH;
			OO = oO;
			CON = cON;
			ORP = oRP;
		}
		public float getPower() {
			return Power;
		}
		public void setPower(float power) {
			Power = power;
		}
		public float getTemp() {
			return Temp;
		}
		public void setTemp(float temp) {
			Temp = temp;
		}
		public float getDepth() {
			return Depth;
		}
		public void setDepth(float depth) {
			Depth = depth;
		}
		public float getPH() {
			return PH;
		}
		public void setPH(float pH) {
			PH = pH;
		}
		public float getOO() {
			return OO;
		}
		public void setOO(float oO) {
			OO = oO;
		}
		public float getCON() {
			return CON;
		}
		public void setCON(float cON) {
			CON = cON;
		}
		public float getORP() {
			return ORP;
		}
		public void setORP(float oRP) {
			ORP = oRP;
		}
		
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public SensorData(long time, float power, float temp, float depth, float pH, float oO, float cON, float oRP) {
		super();
		this.time = time;
		Power = power;
		Temp = temp;
		Depth = depth;
		PH = pH;
		OO = oO;
		CON = cON;
		ORP = oRP;
	}
	@Override
	public String toString() {
		return "{\"time\":" + time + ",\"Power\": " + Power + ",\"Temp\":" + Temp + ", \"Depth\":" + Depth + ", \"PH\":" + PH
				+ ",\"OO\":" + OO + ",\"CON\":" + CON +",\"ORP\":" +ORP +"}";
	}
	
	
	
	
	
	


}
