# Exercises with git and github

Executive summary: by the time you've completed this workshop you will have created three GitHub repositories:

   * A private repository of any name you like, where you'll experiment with git operations. This is used in parts 1 and 3, below.
    
   * **https:://github.com/your-account/CompletedWork**, which we'll use in lieu of the shared **CompletedWork** from now on. This is the focus of part 2.
    
   * **https://github.com/your-account/HelpDesk**, in which you and a partner will make some enhancements to the HelpDesk application.

## Part 1 -- Individual Exercises

1. Create a new repository on GitHub, under your personal profile. You can call it anything you like. Let GitHub create a starter README.md file.

1. Clone the repository to your local filesystem, again anywhere you like. This is **git clone https://github/your-account/your-repo-name**, and it will create a local copy of the repository in a subfolder **your-repo-name** from wherever you run the command. Remaining commands must be run from that subfolder.

1. Add text files to your cloned repository, and put a little content in each file:

        [root of your repo]
          BusSchedule.txt
          Contacts.txt
          Personal
            MyBirthday.txt
            MyFavoriteSong.txt
            MyPassword.txt
            MyPhoto.png *(or any format)*

1. Add a **.gitignore** file to the root, with just this content:

        **/MyPassword.txt

1. Now **git add .** to add all files to the repository.

1. **git status**

1. **git commit -m "Initial commit."**

1. **git status**

1. **git push**

1. **git status**

1. View/refresh the repository in a browser and see that all files but one were pushed.

1. Hmm ... you're now thinking that you didn't really want to publish your contacts. It's too late to .gitignore the file, so **git rm Contacts.txt**.

1. **dir** or **ls**

1. Whoops! By default, the **rm** command will not only remove the file(s) from tracking in the repository, but will also delete them from their location(s) on your file system.

1. **git status**

1. **git reset --hard HEAD**

1. **git status**

1. **dir** or **ls**

1. **git rm --cached Contacts.txt**

1. **dir** or **ls**

1. **git status**

1. **git commit -m "Got rid of contacts." .

1. **git push**

1. **git status**

1. Check in the browser to see that the file is gone from the repository.

1. Let's say you've changed your mind: edit MyFavoriteSong.txt.

1. **git status**

1. Commit and push the change.

1. **git status**

1. Add a file **Notes.txt** to the root.

1. **git status**

1. Modify the **README.md** using the built-in editor as you view the page on GitHub, so that the file summarizes what you've done so far. (You can keep this brief, or go into some detail if you want to explore Markdown.) Commit the change directly in the browser, but do not pull the change to your local repository yet.

1. Change one of the times on your bus schedule, or otherwise modify the file.

1. **git commit -m "Updated bus time."**

1. **git push** -- whoops! You can't push, because your repository is out of synch with the remote repository.

1. **git status**

1. **git pull**

1. **git status**

1. **git push**

## Part 2 -- Submit your Billing1 Project

1. Create a new repo **github.com/*your-id*/CompletedWork**. Make sure that this repository is private, as you will add code to it with "amica" tokens in it. Clone it to your local system.

1. Create a folder **Billing/Billing1** in the cloned repository.

1. Copy the **src** tree from your **Billing1** project into this folder, add it (best to **git add Billing/Billing1/** to add all files), commit it, and push.

1. Add the instructor as a collaborator.

1. Confirm with the instructor that they can clone the repository, and address any permissions issues. For a public repo a link by email is usually fine; for a priacate repo you'll need to add the instructor to the repo as a contributor. (Instructor's GitHub ID is **wwprovost**.)
  
1. If you are not finished with the Billing1 exercise yet, you can update the contents of the repository with your changes when ready, and commit and push.

## Part 3 -- Pair Exercises

You'll work with one partner, and do the first few steps reciprocally with each other's existing repositories from Part 1. If you find yourself in a group of three, then work in a circle: you'll work with one teammate's repository while the third teammate works with yours.

1. Clone your partner's repository.

1. Add a file **Contribution.txt** to the root, commit it, and push -- whoops!

1. Have your partner add you as a contributor to the repository, and then push your new file. This may require creation of an access token. [Meanwhile, your partner will need you to do this for them in your repository.]

1. Push your new file. [Now pull your own repository to see partner's addition.]

    Now do these next steps in synch with your partner, and if in a group of three, do them on both repositories:

1. One of you, add another file **Resource.txt**. The other, edit **Contribution.txt** and save the changes.

1. Each of you, commit your change locally.

1. Each of you, try to push. One of you will be unable to push, so then pull, and then push. Then whoever was able to push originally will need to pull, and you will again have identical content.

1. Each of you, create a branch with **git branch *my-branch***

1. **git branch** and see your new branch created -- and that you are still on the main branch.

1. **git checkout my-branch**

1. **git branch** and see that you are now on *my-branch*.

1. Add files, remove files, make changes ... experiment; but don't commit yet.

1. **git checkout main** and check your new files, changes, or removals. They're all still there, because they weren't committed.

1. **git checkout *my-branch***

1. **git commit -m "Branch stuff"**

1. **git checkout main** and your changes will disappear -- but they're saved in your branch.

1. **git checkout *my-branch*** and they're back!

1. Pull, and then **git push --set-upstream origin *my-branch*** to put your branch on the remote repository. (You only need to set the upstream remote the first time you push; it's recorded and used after that.)

    Now synch up with your partner: you should both have a branch on the remote and be completely in synch.

1. **git checkout --track origin/*partner-branch*** and see partner's version of the repository.

1. **git checkout main**

1. One of you at a time, **git pull**, **git merge *my-branch***, **git commit**, and **git push**. The second merge may require some extra work to resolve conflicts before it can succeed.

## Part 4 -- HelpDesk enhancements

Continue working with your partner on this part.

1. One of you, create a new repository under your personal GitHub account -- again, make sure it's private -- called **HelpDesk3**. Invite your partner to contribute to the repository.

1. Both of you, clone the repository.

1. One of you, copy in the contents of the **HelpDesk2** folder on the course repository, and git add/commit/push this content; and the other can pull it.

1. Each of you, create a project in your IDE, located at the root of the local repository.

1. Add a **.gitignore** file to the repository that will ignore the files generated by your IDE. This will vary by IDE, so do a little research to see what sorts of exclusion patterns make sense. Rmember to exclude folders that hold generated content, such as **bin/**, **output/** etc.

1. Now, each partner, choose a feature to implement in HelpDesk. The exact details of these features aren't critical, but they'll give you each a separate goal and you'll then work concurrently on the code, using git to coordinate.

    * Add a **WAITING** status to the ticket system, representing the state in which the assigned technician is waiting for a response from the originator or from a third party. Add methods **suspend(String reason)** and **resume(String reason)** to the **Ticket** class. These can work like **addNote()** but will effect state transitions to **WAITING** and back to **ASSIGNED** states. Change some of the test code to use the new feature: for example when a technician requests approval from a manager, that could be a call to wait(), and then you should call resume() before calling resolve() on that ticket.

    * Add a **getLatestActivity()** to **HelpDesk**, which should return a stream of the most recent 10 events, system-wide. Add a test for this to the test program.

1. Implement your chosen feature, creating a branch for it and merging the branch when your feature is working. You'll be the only one who needs to work on some of the files that you touch, and then there will be contention over the test program, so you'll need to communicate and be careful around committing and/or merging changes there.

1. Confirm with the instructor that they can clone the repository, and address any permissions issues.

