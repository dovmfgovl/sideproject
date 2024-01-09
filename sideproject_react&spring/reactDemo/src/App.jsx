import { Route, Routes } from 'react-router-dom';
import HomePage from './components/page/HomePage';
import BoardPage from './components/page/BoardPage';
import NoticePage from './components/page/NoticePage';
import NoticeWrite from './components/notice/NoticeWrite';
import NoticeDetail from './components/notice/NoticeDetail';
import NoticeUpdate from './components/notice/NoticeUpdate';
import BoardWrite from "./components/board/BoardWrite";
import BoardDetail from "./components/board/BoardDetail";
import BoardUpdate from "./components/board/BoardUpdate";
function App() {
  return (
    <>
      <Routes>
        <Route path="/" exact={true} element={<HomePage />} />
        <Route path="/notice"  element={<NoticePage />} />
        <Route path="/notice/write"  element={<NoticeWrite />} />
        <Route path="/notice/:id" element={<NoticeDetail />} />
        <Route path="/notice/update/:id" element={<NoticeUpdate />} />
        <Route path="/board"  element={<BoardPage />} />
        <Route path="/board/write"  element={<BoardWrite />} />
        <Route path="/board/:id"  element={<BoardDetail />} />
        <Route path="/board/update/:id" element={<BoardUpdate />} />
      </Routes>
    </>
  );
}

export default App;
