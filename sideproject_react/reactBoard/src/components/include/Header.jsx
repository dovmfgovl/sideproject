import React from 'react'
import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <>
      <Navbar bg="light" expand="lg">
        <Container fluid>
          <Navbar.Brand href="#">리액트캠프</Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />     
          <Navbar.Collapse id="navbarScroll">
            <Nav
              className="me-auto my-2 my-lg-0"
              style={{ maxHeight: '100px' }}
              navbarScroll
            >
              <Link to="/" className='nav-link'>Home</Link>
              {/* <Link to="/login/10/test" className='nav-link'>로그인</Link> */}
              <Link to="/notice" className='nav-link'>공지사항</Link>
              <Link to="/board" className='nav-link'>게시판</Link>
            </Nav>
          </Navbar.Collapse>               
        </Container>
      </Navbar>    
    </>
  )
}

export default Header