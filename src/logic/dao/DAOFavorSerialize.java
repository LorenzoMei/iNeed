package logic.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.entity.Ad;
import logic.entity.Favor;
import logic.entity.User;

public class DAOFavorSerialize extends DAOSerialize implements DAOFavor {

	private final String storeFolderFromSuper = super.readDBPath() + Favor.class.getSimpleName() + File.separator;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static DAOFavorSerialize ref = null;
	
	public static DAOFavorSerialize getReference() {
		
		if (ref == null) {
			ref = new DAOFavorSerialize();
		}
		ref.initializeFavorDB();
		return ref;
	}
	
	private DAOFavorSerialize() {}
	
	private void initializeFavorDB() {
		File storeFolder = new File(this.storeFolderFromSuper);
		if (!storeFolder.exists()) {
			storeFolder.mkdirs();
		}
	}
	
	@Override
	public void storeFavor(Favor favor) {
		DateFormat dateFormatter = new SimpleDateFormat(DAOSerialize.DATE_TIME_FORMAT_STANDARD);
		List <String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(favor.getOffererUsername());
		primaryKeyValues.add(favor.getRequesterUsername());
		primaryKeyValues.add(dateFormatter.format(favor.getDateOfRequest().getTime()));
		try {
			this.store(favor, primaryKeyValues);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
			logger.log(Level.SEVERE, e.toString());
		}

	}

	@Override
	public List<Favor> loadFavors(User requester, User offerer, Calendar dateOfRequest) {
		
		logger.log(Level.INFO, "requester username is: " + requester.getUsername());
		logger.log(Level.INFO, "offerer username is: " + offerer.getUsername());
		logger.log(Level.INFO, "dateOfRequest is: {0}", dateOfRequest);
		
		if (offerer.getUsername() == null) {
			return this.loadFavors(requester);
		}
		else if (dateOfRequest == null) {
			return this.loadFavors(requester, offerer);
		}
		
		List<Favor> favors = new ArrayList<>();
		File storeFolder = new File(this.storeFolderFromSuper);
		String[] fileNames = storeFolder.list();
		
		for (int i = 0; i < fileNames.length; i ++) {
			String[] primaryKeyValues = this.tokenize(fileNames[i]);
			if (primaryKeyValues[0].compareTo(offerer.getUsername()) == 0
					&& primaryKeyValues[1].compareTo(requester.getUsername()) == 0) {
				try {
					String[] dateParts = primaryKeyValues[2].split(DAOSerialize.DATE_TIME_SEPARATOR);
					Calendar.Builder storedCalendarBuilder = new Calendar.Builder();
					storedCalendarBuilder.setDate(Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]) - 1, Integer.valueOf(dateParts[2]));
					storedCalendarBuilder.setTimeOfDay(Integer.valueOf(dateParts[3]), Integer.valueOf(dateParts[4]), Integer.valueOf(dateParts[5]));
					Calendar storedCalendar = storedCalendarBuilder.build();
					String message = String.format("storedCalendar year is %d while dateOfRequest year is %d", storedCalendar.get(Calendar.YEAR), dateOfRequest.get(Calendar.YEAR));
					logger.log(Level.INFO, message);
					if (storedCalendar.get(Calendar.YEAR) == dateOfRequest.get(Calendar.YEAR)
							&& storedCalendar.get(Calendar.MONTH) == dateOfRequest.get(Calendar.MONTH)
							&& storedCalendar.get(Calendar.DAY_OF_MONTH) == dateOfRequest.get(Calendar.DAY_OF_MONTH)) {
						Favor favor = new Favor();
						this.load(favor, Arrays.asList(primaryKeyValues[0], primaryKeyValues[1], primaryKeyValues[2]));
						favors.add(favor);
						
					}
				} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | IOException | ElementInDBNotFoundException e) {
					logger.log(Level.SEVERE, e.toString());
				}
			}
		}
		
		return favors;
	}
	
	private List<Favor> loadFavors(User requester, User offerer){
		List<Favor> favors = new ArrayList<>();
		File storeFolder = new File(this.storeFolderFromSuper);
		String[] fileNames = storeFolder.list();
		
		for (int i = 0; i < fileNames.length; i ++) {
			String[] primaryKeyValues = this.tokenize(fileNames[i]);
			if (primaryKeyValues[0].compareTo(offerer.getUsername()) == 0
					&& primaryKeyValues[1].compareTo(requester.getUsername()) == 0) {
				Favor favor = new Favor();
				try {
					this.load(favor, Arrays.asList(primaryKeyValues[0], primaryKeyValues[1], primaryKeyValues[2]));
					favors.add(favor);
				} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | InstantiationException | IOException
						| ElementInDBNotFoundException e) {
					logger.log(Level.SEVERE, e.toString());
				}
			}
		}
		return favors;
	}
	
	private List<Favor> loadFavors(User requester){
		List<Favor> favors = new ArrayList<>();
		File storeFolder = new File(this.storeFolderFromSuper);
		String[] fileNames = storeFolder.list();
		
		for (int i = 0; i < fileNames.length; i ++) {
			String[] primaryKeyValues = this.tokenize(fileNames[i]);
			if (primaryKeyValues[1].compareTo(requester.getUsername()) == 0) {
				Favor favor = new Favor();
				try {
					this.load(favor, Arrays.asList(primaryKeyValues[0], primaryKeyValues[1], primaryKeyValues[2]));
					favors.add(favor);
				} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException | InstantiationException | IOException
						| ElementInDBNotFoundException e) {
					logger.log(Level.SEVERE, e.toString());
				}
			}
		}
		return favors;
	}

	@Override
	public void loadFavor(Favor favor, User offerer, User requester, Calendar dateOfRequest) throws FavorNotFoundException {
		DateFormat dateFormatter = new SimpleDateFormat(DAOSerialize.DATE_TIME_FORMAT_STANDARD);
		List <String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(offerer.getUsername());
		primaryKeyValues.add(requester.getUsername());
		primaryKeyValues.add(dateFormatter.format(dateOfRequest.getTime()));
		
		try {
			this.load(favor, primaryKeyValues);
		} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException | IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (ElementInDBNotFoundException e) {
			throw new FavorNotFoundException(e.getPath(), e.getCause());
		}
		
	}
	@Override
	public List<Favor> loadFavorsByAd(User requester, Ad ad){
		List<Favor> favors = this.loadFavors(requester);
		this.removeFavorsOfOtherAds(favors, ad);
		return favors;
	}
	@Override
	public List<Favor> loadFavorsByAd(User requester, User offerer, Ad ad){
		List<Favor> favors = this.loadFavors(requester, offerer);
		this.removeFavorsOfOtherAds(favors, ad);
		return favors;
	}
	
	private void removeFavorsOfOtherAds(List<Favor> favors, Ad ad) {
		for (Favor f : favors) {
			if (f.getAdType().compareTo(ad.getClass().getSimpleName()) != 0 
					|| f.getAdId() != ad.getId()) {
				favors.remove(f);
			}
		}
	}

	@Override
	public void deleteFavor(User offerer, User requester, Calendar dateOfRequest) {
		DateFormat dateFormatter = new SimpleDateFormat(DAOSerialize.DATE_TIME_FORMAT_STANDARD);
		List <String> primaryKeyValues = new ArrayList<>();
		primaryKeyValues.add(offerer.getUsername());
		primaryKeyValues.add(requester.getUsername());
		primaryKeyValues.add(dateFormatter.format(dateOfRequest.getTime()));
		try {
			this.delete(new Favor(), primaryKeyValues);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "PATH ERROR!");
			e.printStackTrace();
		}
		
	}
}
