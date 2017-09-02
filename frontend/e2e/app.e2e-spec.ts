import { ExercisesFrontendPage } from './app.po';

describe('exercises-frontend App', () => {
  let page: ExercisesFrontendPage;

  beforeEach(() => {
    page = new ExercisesFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
