package logic.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.misc.NoSuchGetterException;
import logic.misc.ReflectionMiscellaneous;

public class DAOSerializeOOS implements ObjectOutput {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ObjectOutputStream writer;
	
	public DAOSerializeOOS(OutputStream dest) throws IOException {
		writer = new ObjectOutputStream(dest);
	}
	
	@Override
	public void writeBoolean(boolean v) throws IOException {
		this.writer.writeBoolean(v);

	}

	@Override
	public void writeByte(int v) throws IOException {
		this.writer.writeByte(v);

	}

	@Override
	public void writeShort(int v) throws IOException {
		this.writeShort(v);
	}

	@Override
	public void writeChar(int v) throws IOException {
		this.writer.writeChar(v);

	}

	@Override
	public void writeInt(int v) throws IOException {
		this.writer.writeInt(v);

	}

	@Override
	public void writeLong(long v) throws IOException {
		this.writer.writeLong(v);

	}

	@Override
	public void writeFloat(float v) throws IOException {
		this.writer.writeFloat(v);

	}

	@Override
	public void writeDouble(double v) throws IOException {
		this.writer.writeDouble(v);

	}

	@Override
	public void writeBytes(String s) throws IOException {
		this.writer.writeBytes(s);

	}

	@Override
	public void writeChars(String s) throws IOException {
		this.writer.writeChars(s);

	}

	@Override
	public void writeUTF(String s) throws IOException {
		this.writer.writeUTF(s);
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		EntitySerializable buffer;
		try {
			buffer = this.entityToSerializable(obj);
			writer.writeObject(buffer);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}

	@Override
	public void write(int b) throws IOException {
		this.writer.write(b);

	}

	@Override
	public void write(byte[] b) throws IOException {
		this.writer.write(b);

	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		this.writer.write(b, off, len);

	}

	@Override
	public void flush() throws IOException {
		this.writer.flush();

	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}
	
	private void buildAttributes(Class<?> currentClass, List<Field> attributes){
		Class<?> superClass = (Class<?>) currentClass.getGenericSuperclass();		
		Field [] currentAttributes = currentClass.getDeclaredFields();
		Collections.addAll(attributes, currentAttributes);
		if (superClass != Object.class) {
			this.buildAttributes(superClass, attributes);			
		}
	}
	
	private EntitySerializable entityToSerializable(Object obj) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
		EntitySerializable buffer = new EntitySerializable();
		buffer.setEntityClassName(obj.getClass().getName());
		List<Field> attributesList = new ArrayList<>();
		this.buildAttributes(obj.getClass(), attributesList);
		
		Field[] attributes = new Field[attributesList.size()];
		attributesList.toArray(attributes);		
		
		for (int i = 0; i < attributes.length; i ++) {

			if ((Modifier.isPrivate(attributes[i].getModifiers()) || Modifier.isProtected(attributes[i].getModifiers()))
					&& !attributes[i].isSynthetic()) {					
				Object attrVal = null;
				try (ObjectOutputStream tester = new ObjectOutputStream(new ByteArrayOutputStream())) {
					attrVal = ReflectionMiscellaneous.getGetter(attributes[i].getName(), obj).invoke(obj, (Object[]) null);
					tester.writeObject(attrVal);
				} catch (NotSerializableException e) {			
					attrVal = this.entityToSerializable(attrVal);
				}
				catch(NoSuchGetterException e) {
					logger.log(Level.SEVERE, "No such get method for attribute " + e.getAttrName());
				}
				finally {
					String toLog = String.format("adding to buffer: %s=%s", attributes[i].getName(), attrVal.toString());
					logger.log(Level.INFO, toLog);
					buffer.getAttributes().put(attributes[i].getName(), (Serializable) attrVal);
				}
			}
		}
		return buffer;
	}

}
