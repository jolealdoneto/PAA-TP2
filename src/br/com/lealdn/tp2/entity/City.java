package br.com.lealdn.tp2.entity;

public class City implements Comparable<City> {
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private int id;

    public City(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

	@Override
	public int compareTo(City o) {
		return new Integer(this.getId()).compareTo(o.getId());
	}

    @Override
    public String toString() {
        return ((Integer)this.getId()).toString();
    }
}
