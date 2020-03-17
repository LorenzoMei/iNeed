package logic.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.misc.NoSuchSetterException;
import logic.misc.ReflectionMiscellaneous;

public class DAOSerializeOIS implements ObjectInput {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ObjectInputStream reader;
	private Object dest;
	
	public DAOSerializeOIS (InputStream src, Object dest) throws IOException {
		this.reader = new ObjectInputStream(src);
		this.dest = dest;
	}
	
	@Override
	public void readFully(byte[] b) throws IOException {
		this.reader.readFully(b);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		this.reader.readFully(b, off, len);

	}

	@Override
	public int skipBytes(int n) throws IOException {
		return this.reader.skipBytes(n);
	}

	@Override
	public boolean readBoolean() throws IOException {
		return this.reader.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return this.reader.readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return this.reader.readUnsignedByte();
	}

	@Override
	public short readShort() throws IOException {
		return this.reader.readShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return this.reader.readUnsignedShort();
	}

	@Override
	public char readChar() throws IOException {
		return this.reader.readChar();
	}

	@Override
	public int readInt() throws IOException {
		return this.reader.readInt();
	}

	@Override
	public long readLong() throws IOException {
		return this.reader.readLong();
	}

	@Override
	public float readFloat() throws IOException {
		return this.reader.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return this.reader.readDouble();
	}

	@Override
	public String readLine() throws IOException {
		return null;
	}

	@Override
	public String readUTF() throws IOException {
		return this.reader.readUTF();
	}

	@Override
	public Object readObject() throws ClassNotFoundException, IOException {
		
		try {
			EntitySerializable buffer = (EntitySerializable) reader.readObject();
			this.serializableToEntity(this.dest, buffer);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			logger.log(Level.SEVERE, e.toString());
		}
		return dest;
	}

	@Override
	public int read() throws IOException {
		return this.reader.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		return this.reader.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return this.reader.read(b, off, len);
	}

	@Override
	public long skip(long n) throws IOException {
		return this.reader.skip(n);
	}

	@Override
	public int available() throws IOException {
		return this.reader.available();
	}

	@Override
	public void close() throws IOException {
		this.reader.close();

	}
	
	private void buildAttributes(Class<?> currentClass, List<Field> attributes){
		Class<?> superClass = (Class<?>) currentClass.getGenericSuperclass();		
		Field [] currentAttributes = currentClass.getDeclaredFields();
		Collections.addAll(attributes, currentAttributes);
		if (superClass != Object.class) {
			this.buildAttributes(superClass, attributes);			
		}
	}
	
	private void serializableToEntity(Object obj, EntitySerializable buffer) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
		
		List<Field> attributesList = new ArrayList<>();
		this.buildAttributes(obj.getClass(), attributesList);	
		Field[] attributes = new Field[attributesList.size()];
		attributes = attributesList.toArray(attributes);
		
		Object buffVal = null;
		Object nestedObj = null;
		
		for (int j = 0; j < attributes.length; j ++) {
				buffVal = buffer.getAttributes().get(attributes[j].getName());
				if ((Modifier.isPrivate(attributes[j].getModifiers()) || Modifier.isProtected(attributes[j].getModifiers())) && !attributes[j].isSynthetic() && !EntitySerializable.class.isInstance(buffVal)){		
						try{
							ReflectionMiscellaneous.getSetter(attributes[j].getName(), obj).invoke(obj, buffVal);
							String toLog = String.format("loaded value : %s", buffVal);
							logger.log(Level.INFO, toLog);
						}
						catch(NoSuchSetterException e) {
							logger.log(Level.SEVERE, "No such set method for attribute " + e.getAttrName());
						}
				}
				else if ((Modifier.isPrivate(attributes[j].getModifiers()) || Modifier.isProtected(attributes[j].getModifiers())) && EntitySerializable.class.isInstance(buffVal)) {
						nestedObj = Class.forName(((EntitySerializable) buffer.getAttributes().get(attributes[j].getName())).getEntityClassName()).newInstance();
						this.serializableToEntity(nestedObj, (EntitySerializable) buffVal);
						try{
							ReflectionMiscellaneous.getSetter(attributes[j].getName(), obj).invoke(obj, nestedObj);
						}
						catch(NoSuchSetterException e) {
							logger.log(Level.SEVERE, "No such set method for attribute " + e.getAttrName());
						}
				}
		}
	}

}
