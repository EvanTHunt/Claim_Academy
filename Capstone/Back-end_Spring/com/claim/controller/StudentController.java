package com.claim.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.claim.entity.Bounds;
import com.claim.entity.Image;
import com.claim.entity.Student;
import com.claim.repository.ImageRepository;
import com.claim.repository.StudentRepository;

@RestController
@CrossOrigin
// ^^^^^DO NOT DO THIS IN THE REAL WORLD
public class StudentController 
{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	
	
	@RequestMapping(value = "/getGallery", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Image>> getImagesByEmail(@RequestBody Student student)
	{
		
		String email = student.getEmail();
		List<Image> images = this.imageRepository.getImagesByEmail(email);
		return new ResponseEntity<>(images, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/getImagesByLatLng", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Image>> getImagesByLatLng(@RequestBody Bounds bounds)
	{
		double wBound = bounds.getwBound();
		double eBound = bounds.geteBound();
		double sBound = bounds.getsBound();
		double nBound = bounds.getnBound();
		
		if(wBound > eBound)
		{
			List<Image> images = this.imageRepository.getAllImagesByLatLngDiscontinuity(wBound, eBound, sBound, nBound);
			return new ResponseEntity<>(images, HttpStatus.OK);
		}
		else
		{
			List<Image> images = this.imageRepository.getAllImagesByLatLngNormal(wBound, eBound, sBound, nBound);
			return new ResponseEntity<>(images, HttpStatus.OK);
		}
		
	}
	
	
	@RequestMapping(value = "/updateImage", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateImage(@RequestBody Image image)
	{
		this.imageRepository.save(image);
	}
	
	
	@RequestMapping(value = "/submitImage", 
			method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void submitImage(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("lat") double lat,
			@RequestParam("lng") double lng, @RequestParam("email") String email) throws IOException
	{	
		
		String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
		
		Image image = new Image(base64Image, email, title, description, lat, lng);
		
		this.imageRepository.save(image);
	}
	
	
	@RequestMapping(value = "/submitStudentDetails", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void submitStudentDetails(@RequestBody Student student)
	{
		this.studentRepository.save(student);
	}
	
	
	@RequestMapping(value = "/findStudentById",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Student> findStudent(String email)
	{
		Student student = this.studentRepository.findById(email).get();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/login", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Student> login(@RequestBody Student student)
	{
		// store the user entered password to test against value in database
		String enteredPassword = student.getPassword();
		
		// retrieve Optional from database based on user entered email, and return UNAUTHORIZED if Optional is null
		Optional<Student> dbStudent = this.studentRepository.findById(student.getEmail());
		if(!dbStudent.isPresent())
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		// return student object if password is correct
		if(enteredPassword.equals(dbStudent.get().getPassword()))
		{
			return new ResponseEntity<>(dbStudent.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@RequestMapping(value = "/getStudentList",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Student>> getStudentList()
	{
		List<Student> studentList = this.studentRepository.findAll();
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}
	
}
