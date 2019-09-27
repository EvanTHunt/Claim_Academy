import React, { Component } from 'react';
import axios from 'axios'

class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state={
      student:{
        firstName:'',
        lastName:'',
        age:'',
        telephone:'',
        email:'',
        password:''
      }
    }
  }

  signUpSubmitHandler = (event) => {
    event.preventDefault();
    axios.post("http://localhost:4200/capstone/submitStudentDetails", this.state.student)
    .then(response => {
      this.props.history.push('/thank-you');
    }).catch(error => {
      // error message here
    });
  }

  signUpChangeHandler = (event) => {
    const key = event.target.name;
    const value = event.target.value;
    const student = {...this.state.student};
    student[key] = value;
    this.setState({
      student:student
    });
  }
 
  render() {
    return (
      <div className="signuppic">
        <form className="container" onSubmit={this.signUpSubmitHandler}>
          <h1>Sign up here.</h1><br></br>
          <div className="form-group">
            <label for="exampleInputPassword1">First Name</label>
            <input name='firstName' onChange={this.signUpChangeHandler} value={this.state.student.firstName} type="text" className="form-control" placeholder="First Name" required="required"/>
          </div>
          <div className="form-group">
            <label for="exampleInputPassword1">Last Name</label>
            <input name='lastName' onChange={this.signUpChangeHandler} value={this.state.student.lastName} type="text" className="form-control" placeholder="Last Name" required="required"/>
          </div>
          <div className="form-group">
            <label for="exampleInputPassword1">Age</label>
            <input name='age' onChange={this.signUpChangeHandler} value={this.state.student.age} type="number" min="0" step="1" className="form-control" placeholder="Age" required="required"/>
          </div>
          <div className="form-group">
            <label for="exampleInputPassword1">Phone Number (Must be ten digits)</label>
            <input name='telephone' onChange={this.signUpChangeHandler} value={this.state.student.telephone} type="tel" pattern="[0-9]{10}" className="form-control" placeholder="Phone Number" required="required"/>
          </div>
          <div className="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input name='email' onChange={this.signUpChangeHandler} value={this.state.student.email} type="email" className="form-control" placeholder="Email" required="required"/>
          </div>
          <div className="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input name='password' onChange={this.signUpChangeHandler} value={this.state.student.password} type="password" className="form-control" placeholder="Password" required="required"/>
          </div>
          <button type="submit" className="btn btn-secondary">Sign Up</button>
        </form>
      </div>
    );
  }
}

export default SignUp;