import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios'

class Header extends Component {
  state={
    email:'',
    password:''
  };

  signInSubmitHandler = (event) => {
    event.preventDefault();

    const student = {
      email:this.state.email,
      password:this.state.password
    };

    axios.post("http://localhost:4200/capstone/login", student)
    .then(response => {
      this.setState({
        email:'',
        password:''
      });
      const loginStudent = response.data;
      localStorage.setItem("loggedInStudent",JSON.stringify(loginStudent));
      this.props.history.push('/home/mygallery');
    }).catch(error => {
      // display error message
    });
  }

  signUpChangeHandler = (event) => {
    const key = event.target.name;
    const value = event.target.value;
    this.setState({
        [key]:value
    });
  }

  signOut = () => {
    localStorage.removeItem("loggedInStudent");
    this.props.history.push("/");
  }

  render() {
    let defaultSignInSignOut = (
      <form className="form-inline mt-2 mt-md-0" onSubmit={this.signInSubmitHandler}>
        <input name="email" value={this.state.email} onChange={this.signUpChangeHandler} className="form-control mr-sm-2" type="text" placeholder="Email"/>
        <input name="password" value={this.state.password} onChange={this.signUpChangeHandler} className="form-control mr-sm-2" type="password" placeholder="Password"/>
        <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Sign In</button>
      </form>
    );

    if(localStorage.getItem("loggedInStudent")){
      defaultSignInSignOut = <button onClick={this.signOut} className="btn btn-outline-success my-2 my-sm-0" style={{color:'white', borderColor:'white'}} type="button">Sign Out</button>
    }
    
    return (
      <header className="margin-bottom-50">
        <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
          <img src={require("./icons/logo.png")} className="" style={{width:'3%'}} alt=''/>
          <Link className="navbar-brand" to="/">&nbsp;PhotoQuest</Link>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarCollapse">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item active">
              <Link className="nav-link sr-only" to="/about-us">About Us<span>(current)</span></Link>
            </li>
            {/*<li className="nav-item">
              <a className="nav-link" href="#">Link</a>
            </li>
            <li className="nav-item">
              <a className="nav-link disabled" href="#">Disabled</a>
            </li>*/}
          </ul>
          {defaultSignInSignOut}
          </div>
        </nav>
      </header>
    );
  }
}

export default Header;