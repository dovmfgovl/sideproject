import React from 'react';
//import { createRoot } from 'react-dom/client';
import ReactDom from 'react-dom';
import './index.css';
//import App from './App';
import reportWebVitals from './reportWebVitals';
//import Library from './chapter_03/Library';
//import Clock from './chapter_04/Clock';
//import CommentList from './chapter_05/CommentList';
//import NotificationList from './chapter_06/NotificationList';
import Accommodate from './chapter_07/Accommodate';

// createRoot(document.getElementById('root')).render(
//   <React.StrictMode>
//     <Accommodate />
//   </React.StrictMode>
// );
ReactDom.render(
  <React.StrictMode>
    <Accommodate />
  </React.StrictMode>,
  document.getElementById('root')
);
reportWebVitals();
