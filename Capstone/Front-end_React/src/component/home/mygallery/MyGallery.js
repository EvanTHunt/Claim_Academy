import React, { Component } from 'react';
import axios from 'axios';
import Gallery from 'react-grid-gallery';
import UpdateModal from './UpdateModal';

class MyGallery extends Component {
  constructor(){
    super()
    this.state = {
      images:[],
      selectedImage:undefined
    }
  }
  
  componentDidMount(){
    this.props.setSelectedPage('mygallery');

    const loggedInStudent = JSON.parse(localStorage.getItem("loggedInStudent"));
    const student = {
      email:loggedInStudent.email,
    };

    axios.post("http://localhost:4200/capstone/getGallery", student)
    .then(response => {
      this.setState({images:response.data});
    }).catch(error => {
      // display error message
      window.alert('error getting gallery');
    });
  }
  
  selectImageHandler = (index) => {
    this.setState({selectedImage:this.state.images[index]});
  }

  onModalClose = () => {
    this.setState({selectedImage:undefined});
  }

  handleClickMap = (t, map, coord) => {
    const { latLng } = coord;
    const lat = latLng.lat();
    const lng = latLng.lng();
    const image = {...this.state.selectedImage};
    image.lat = lat;
    image.lng = lng;
    this.setState({
      selectedImage:image
    });
  }
  
  formChangeHandler = (event) => {
    const key = event.target.name;
    const value = event.target.value;
    const image = {...this.state.selectedImage};
    image[key] = value;
    this.setState({
      selectedImage:image
    });
  }

  fileChangeHandler = (event) => {
    const file = event.target.files[0];
    this.setState({
      file:file
    });
  }

  render() {

    let convertedImages = [];
    let img = null;

    if(this.state.images.length > 0){
      for(let i = 0; i < this.state.images.length; i++){
        img = new Image();
        img.src = 'data:image/jpeg;base64,' + this.state.images[i].file
        convertedImages.push({
          src:'data:image/jpeg;base64,' + this.state.images[i].file,
          thumbnail:'data:image/jpeg;base64,' + this.state.images[i].file,
          thumbnailWidth:img.width,
          thumbnailHeight:img.height,
          caption: "\"" + this.state.images[i].title + "\""
        });
      }
    }

    return (
      <div>
        <Gallery onSelectImage={this.selectImageHandler} enableImageSelection={true} images={convertedImages} rowHeight={250} margin={4}/>
        {this.state.selectedImage!==undefined ? <UpdateModal 
                                                  close={this.onModalClose} image={this.state.selectedImage}
                                                  handleClickMap={this.handleClickMap} formChangeHandler={this.formChangeHandler}
                                                  fileChangeHandler={this.fileChangeHandler}/> : ''}
      </div>  
    );
  }
}

export default MyGallery;