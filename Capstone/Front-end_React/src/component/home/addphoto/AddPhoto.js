import React, { Component } from 'react';
import axios from 'axios';
import AddPhotoForm from './AddPhotoForm';

class AddPhoto extends Component {
  loggedInStudent = JSON.parse(localStorage.getItem("loggedInStudent"));
  state = {
    file:null,
    image: {
      email:this.loggedInStudent.email,
      title:'',
      description:'',
      lat:undefined,
      lng:undefined
    }
  }

  componentDidMount(){
    this.props.setSelectedPage('addphoto');
  }

  handleClickMap = (t, map, coord) => {
    const { latLng } = coord;
    const lat = latLng.lat();
    const lng = latLng.lng();
    const image = {...this.state.image};
    image.lat = lat;
    image.lng = lng;
    this.setState({
      image:image
    });
  }
  
  formChangeHandler = (event) => {
    const key = event.target.name;
    const value = event.target.value;
    const image = {...this.state.image};
    image[key] = value;
    this.setState({
      image:image
    });
  }

  fileChangeHandler = (event) => {
    const file = event.target.files[0];
    this.setState({
      file:file
    });
  }

  imageSubmitHandler = (event) => {
     //event.preventDefault();
     const formData = new FormData();
     formData.append('file',this.state.file);
     formData.append('title',this.state.image.title);
     formData.append('description',this.state.image.description);
     formData.append('lat',this.state.image.lat);
     formData.append('lng',this.state.image.lng);
     formData.append('email',this.state.image.email);
    axios.post("http://localhost:4200/capstone/submitImage", formData)
    .then(response => {
      this.setState({
        file:null,
        image: {
          title:'',
          description:'',
          lat:undefined,
          lng:undefined
        }
      })
      console.log(this.state);
    }).catch(error => {
      // display error message
      window.alert('error submitting image');
    });
  }
  
  // handleMapReady(mapProps,map) {
  //   map.setOptions({
  //     draggableCursor: "default",
  //     draggingCursor: "pointer"
  //   });
  // }

  render() {
    return (
      <div>

        <AddPhotoForm center={{ lat: 38.63, lng: -90.22}} hideFileAttach={false} image={this.state.image} imageSubmitHandler={this.imageSubmitHandler} handleClickMap={this.handleClickMap} formChangeHandler={this.formChangeHandler} fileChangeHandler={this.fileChangeHandler}/>

      </div>
    );
  }
}

// <div className='margin-left-35per'>
//           <Map
//             style={{ height: '75vh', width: '60%' }}
//             google={this.props.google}
//             zoom={10}
//             initialCenter={{ lat: 38.63, lng: -90.22}}
//             onClick={this.handleClickMap}
//             onReady={this.handleMapReady}
//           >
//             <Marker position={{lat:this.state.image.lat, lng:this.state.image.lng}}></Marker>
//           </Map>
//         </div>
//         <div style={{ height: '75vh', width: '30%'}}>
//           <form onSubmit={this.imageSubmitHandler}>
//             <div className="form-group">
//               <label className="font-large" htmlFor="exampleFormControlInput1">&raquo;Latitude / Longitude</label>
//               <input name="lat" value={this.state.image.lat} type="number" onChange={this.formChangeHandler} required="required" className="form-control" id="exampleFormControlInput1" placeholder="Select a location on the map"/><br></br>
//               <input name="lng" value={this.state.image.lng} type="number" onChange={this.formChangeHandler} required="required" className="form-control" id="exampleFormControlInput1" placeholder="Select a location on the map"/>  
//             </div><br></br>
//             <div className="form-group">
//               <label className="font-large" htmlFor="exampleFormControlInput1">&raquo;Photo title</label>
//               <input name="title" value={this.state.image.title} onChange={this.formChangeHandler} type="text" required="required" className="form-control" id="exampleFormControlInput1" placeholder="Give your photo a unique name"/>
//             </div><br></br>
//             <div className="form-group">
//               <label className="font-large" htmlFor="exampleFormControlTextarea1">&raquo;Description</label>
//               <textarea name="description" value={this.state.image.description} onChange={this.formChangeHandler} required="required" className="form-control" id="exampleFormControlTextarea1" placeholder="Tell us specific details about your photo, such as the weather conditions, time of day, special location details, or camera techniques used" rows="5"></textarea>
//             </div><br></br>
//             <div>
//               <label className="font-large">&raquo;Attach Image</label>
//               <input name="image" type="file" onChange={this.fileChangeHandler}></input>
//             </div><br></br>
//             <button type="submit" className="btn btn-secondary">Submit</button>          
//           </form>
//         </div>

// export default GoogleApiWrapper({
//   apiKey: 'AIzaSyBrTDE7NI5aYs7ypjQzGW-q0c6AhRgX3tI'
// })(AddPhoto);

export default AddPhoto;